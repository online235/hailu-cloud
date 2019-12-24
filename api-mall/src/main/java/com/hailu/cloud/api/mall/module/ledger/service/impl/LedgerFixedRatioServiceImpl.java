package com.hailu.cloud.api.mall.module.ledger.service.impl;

import com.hailu.cloud.api.mall.module.goods.dao.GoodsToMapper;
import com.hailu.cloud.api.mall.module.goods.dao.OrderMapper;
import com.hailu.cloud.api.mall.module.goods.vo.SpecVo;
import com.hailu.cloud.api.mall.module.goods.vo.vm.OrderGoodsVm;
import com.hailu.cloud.api.mall.module.ledger.service.IIncomeService;
import com.hailu.cloud.api.mall.module.ledger.service.ILedgerService;
import com.hailu.cloud.api.mall.module.user.dao.UserInfoMapper;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.entity.member.ShopMember;
import com.hailu.cloud.api.mall.module.user.service.IMemberDetailService;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.redis.enums.RedisEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 按固定比例分账
 * 分销处理
 *
 * @Author junpei.deng
 * @Date 2019/11/03 14:52
 */
@Slf4j
@Service
public class LedgerFixedRatioServiceImpl implements ILedgerService {
    @Resource
    private IIncomeService iIncomeService;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodsToMapper goodsToMapper;

    @Autowired
    private RedisStandAloneClient redisClient;

    @Value("${merchant.commission.rate.regional-agent:0.15}")
    private BigDecimal regionalAgent;

    @Value("${merchant.commission.rate.service-agent:0.85}")
    private BigDecimal serviceAgent;

    @Resource
    private IMemberDetailService memberDetailService;

    @Override
    public void editInvitation(String parentId) {
        try {
            //获取邀请者信息
            ShopMember invitationMember = userInfoMapper.byIdFindUser(parentId);
            if (invitationMember == null) {
                log.info("分红 邀请人ID为：{}，查询不到该用户", parentId);
                return;
            }
            //判断刚用户是否为普通用户
            if (invitationMember.getMerchantType() == 1) {
                //区域代理
                iIncomeService.addAccountByInvitation(parentId, new BigDecimal(260), "分享收益", null, 2);
            } else if (invitationMember.getMerchantType() == 2) {
                //服务商   服务商得到200   上级区域代理得到60
                iIncomeService.addAccountByInvitation(parentId, new BigDecimal(200), "分享收益", null, 2);
                iIncomeService.addAccountByInvitation(invitationMember.getSuperiorMember(), new BigDecimal(60), "名下服务商分享收益", null, 2);
            } else {
                log.info("分红 邀请人ID为：{}，为普通用户，不参与分红", parentId);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void editInvitationProvider(ShopMember parentUserInfoVo, BigDecimal money) {
        try {
            //邀请奖金
            BigDecimal moneyPrice = BigDecimal.valueOf(0);
            //祖级邀请奖金
            BigDecimal parentMoney = BigDecimal.valueOf(0);
            boolean parentFlag = false;
            if (parentUserInfoVo.getMerchantType() == 1) {
                //如果上级为区域代理，则获取百分之四十五的提成
                moneyPrice = money.multiply(BigDecimal.valueOf(0.45));
            } else if (parentUserInfoVo.getMerchantType() == 2) {
                //如果上级为服务商，则获取百分之三十的提成
                moneyPrice = money.multiply(BigDecimal.valueOf(0.30));
                if (!"0".equals(parentUserInfoVo.getSuperiorMember())) {
                    //如果祖级的区域代理不为海露，则获取百分之十五的提成
                    parentFlag = true;
                    parentMoney = money.multiply(BigDecimal.valueOf(0.15));
                }
            }
            iIncomeService.addAccountByInvitation(parentUserInfoVo.getUserId(), moneyPrice, "邀请用户成为服务商", null, 2);
            if (parentFlag) {
                iIncomeService.addAccountByInvitation(parentUserInfoVo.getSuperiorMember(), parentMoney, "名下服务商邀请用户成为服务商", null, 2);
            }
        } catch (Exception e) {
            log.error("处理分销错误" + e.getMessage(), e);
        }

    }

    @Override
    public void  distributionGoods(Integer orderId, String userId) {

        ShopMember userInfo = userInfoMapper.byIdFindUser(userId);
        BigDecimal orderAllMoney = BigDecimal.valueOf(0);
        //如果购买者为服务商或者区代 则不参与分销
        if (userInfo.getMerchantType() == 1 || userInfo.getMerchantType() == 2) {
            return;
        }
        //根据订单ID获取订单商品信息
        List<OrderGoodsVm> orderGoodsList = orderMapper.findOrderVmByOrderId(orderId);
        String invitaionUserId = null;
        for (OrderGoodsVm o : orderGoodsList) {
            try {
                //获取分享人ID
                invitaionUserId = redisClient.stringGet(RedisEnum.DB_2.ordinal(), Constant.REDIS_INVITATION_MEMBER_POVIDER_CACHE + userId);
                //判断商品是否参与推广 且 查询是否有分享人
                if (o.getIsPopularize() == 0 || StringUtils.isBlank(invitaionUserId)) {
                    continue;
                }
                //获取到规格ID
                SpecVo spec = goodsToMapper.findSpec(o.getSpecId());
                //提成、如果为空不处理
                BigDecimal commission = spec.getCommission();
                if (commission == null) {
                    continue;
                }
                //乘以数量
                commission = commission.multiply(BigDecimal.valueOf(o.getGoodsNum()));
                orderAllMoney = orderAllMoney.add(commission);
                //处理运费
                BigDecimal freight = BigDecimal.valueOf(12);
                if (commission.compareTo(freight) < -1) {
                    //提成小于运费直接返回
                    continue;
                }
                commission = commission.subtract(freight);
                /**处理分享人的分红**/
                BigDecimal money = BigDecimal.valueOf(0);
                ShopMember userInfoParent = userInfoMapper.byIdFindUser(invitaionUserId);
                //0代表是区域代理为海露，不参与分销，自动划入账号中
                if ("0".equals(userInfoParent.getSuperiorMember())) {
                    continue;
                }
                if (userInfoParent.getMerchantType() == 1) {
                    //如果邀请人为区域代理
                    money = commission;
                } else if (userInfoParent.getMerchantType() == 2) {
                    //分享人为服务商，获取百分之85，他的上级区域代理获取百分之十五
                    money = commission.multiply(serviceAgent);
                    ShopMember userInfoGrand = userInfoMapper.byIdFindUser(userInfoParent.getSuperiorMember());
                    if (!"0".equals(userInfoGrand.getSuperiorMember())) {
                        iIncomeService.addAccountByInvitation(userInfoGrand.getUserId(), commission.multiply(regionalAgent), "旗下商家购买商品分销", String.valueOf(orderId), 1);
                        //销售业绩
                        memberDetailService.addTotal(userInfoGrand.getUserId(),null,null,null,null
                        ,commission.multiply(regionalAgent));
                    }
                }
                iIncomeService.addAccountByInvitation(invitaionUserId, money, "旗下商家购买商品分销", String.valueOf(orderId), 1);
                //销售业绩
                memberDetailService.addTotal(invitaionUserId,null,null,null,null
                        ,money);

                //销售总额
                memberDetailService.addTotal(userId,orderAllMoney,null,null,null,null);
            } catch (Exception e) {
                log.error("分红失败 !!   处理商品ID为{},会员ID：{}，分享者ID{}： ：{}", o.getGoodsId(), userId, invitaionUserId, e.getMessage());
            }
        }
    }
}

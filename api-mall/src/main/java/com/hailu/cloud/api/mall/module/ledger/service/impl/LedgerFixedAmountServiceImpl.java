package com.hailu.cloud.api.mall.module.ledger.service.impl;

import com.hailu.cloud.api.mall.module.goods.dao.OrderMapper;
import com.hailu.cloud.api.mall.module.goods.vo.vm.OrderGoodsVm;
import com.hailu.cloud.api.mall.module.ledger.service.IIncomeService;
import com.hailu.cloud.api.mall.module.ledger.service.ILedgerService;
import com.hailu.cloud.api.mall.module.user.dao.UserInfoMapper;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import com.hailu.cloud.common.entity.member.ShopMember;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 按固定金额分账
 * 公式：
 * 商品：每个商品有固定金额存在数据库里面查询
 * 购买海露会员：
 * 分销处理
 *
 * @Author junpei.deng
 * @Date 2019/11/03 14:52
 */
@Slf4j
@Service(value = "LedgerServiceImpl")
@Deprecated
public class LedgerFixedAmountServiceImpl implements ILedgerService {

    @Resource
    private IIncomeService iIncomeService;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private OrderMapper orderMapper;

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

    /**
     * 分销商品
     *
     * @param orderId
     */
    @Override
    public void distributionGoods(Integer orderId, String userId) {

        try {

            UserInfoVo userInfo = userInfoService.userInfoQueryByUserId(userId);
            //校验上级ID是否为空
            String superiorMember = userInfo.getSuperiorMember();
            if (StringUtils.isBlank(superiorMember)) {
                log.info("用户userId为：{}没有上级ID，不参与分销", userId);
                return;
            }
            UserInfoVo superUserInfo = userInfoService.userInfoQueryByUserId(superiorMember);
            int merchantType = superUserInfo.getMerchantType();
            if (merchantType == 0) {
                log.info("用户userId为：{},无商户类型，不参与分销", superiorMember);
                return;
            }

            //根据订单ID获取订单商品信息
            List<OrderGoodsVm> orderGoodsList = orderMapper.findOrderVmByOrderId(orderId);
            BigDecimal money = new BigDecimal("0");
            BigDecimal parentMoney = BigDecimal.valueOf(0);
            boolean parentFlag = false;
            //遍历循环，处理商户分销
            for (OrderGoodsVm o : orderGoodsList) {
                //根据商品ID获取商品信息
                if (merchantType == 1) {
                    //如果是区域代理，则使用区域代理提成
                    Integer region = o.getRegionalAgentCommission();
                    if (region != null) {
                        money = money.add(BigDecimal.valueOf(region));
                    }
                } else if (merchantType == 2) {
                    parentFlag = true;
                    //如果是服务商，则使用服务商代理提成
                    Integer servicePro = o.getServiceProviderCommission();
                    if (servicePro != null) {
                        money = money.add(BigDecimal.valueOf(servicePro));
                    }
                    parentMoney = parentMoney.add(BigDecimal.valueOf(o.getRegionalAgentCommission() - o.getServiceProviderCommission()));
                }
            }
            iIncomeService.addAccountByInvitation(superiorMember, money, "旗下商家购买商品分销", String.valueOf(orderId), 1);
            //给服务商的上级加提成
            if (parentFlag) {
                UserInfoVo parentUserInfo = userInfoService.userInfoQueryByUserId(superiorMember);
                //0代表是区域代理为海露，不参与分销，自动划入账号中
                if (!"0".equals(parentUserInfo.getSuperiorMember())) {
                    iIncomeService.addAccountByInvitation(parentUserInfo.getSuperiorMember(), parentMoney, "旗下商家购买商品分销", String.valueOf(orderId), 1);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void editInvitationProvider(ShopMember parentUserInfo, BigDecimal money) {

    }
}

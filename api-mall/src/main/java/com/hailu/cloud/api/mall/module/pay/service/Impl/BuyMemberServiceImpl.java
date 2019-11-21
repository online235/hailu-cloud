package com.hailu.cloud.api.mall.module.pay.service.Impl;


import com.hailu.cloud.api.mall.module.pay.service.AbstractPayService;
import com.hailu.cloud.api.mall.module.pay.vo.PayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service.Impl
 * @Author: junpei.deng
 * @CreateTime: 2019-10-24 11:15
 * @Description: 购买平台会员
 */
@Service
@Slf4j
public class BuyMemberServiceImpl extends AbstractPayService {
    @Override
    public Map<String, Object> createOrder(PayVo payVo) {
        return null;
    }

    @Override
    public Map<String, Object> callback(Map<String, Object> params) throws Exception {
        return null;
    }

    //    @Autowired
//    private XinAnPayService xinAnPayService;
//
//    @Autowired
//    private XinAnOrderService xinAnOrderService;
//
//    @Resource
//    private XaOrderMapper xaOrderMapper;
//
//    @Autowired
//    private IUserInfoService userInfoService;
//
//    @Autowired
//    private IIncomeService iIncomeService;
//
//    @Autowired
//    private XinAnPayExpandService xinAnPayExpandService;
//
//    @Resource
//    private UserInfoMapper userInfoMapper;
//
//    @Autowired
//    private LedgerFixedRatioServiceImpl ledgerFixedRatioService;
//
//    @Autowired
//    private RedisKit redisClient;
//
//    @Override
//    public Map<String, Object> createOrder(PayVo payVo) {
//        Map<String, Object> data = new HashMap<>();
//        BigDecimal money = new BigDecimal(payVo.getMoney());
//
//        //检验省市区信息不能为空
//        if (payVo.getProvinceId() == null || payVo.getCityId() == null || payVo.getAreaId() == null || StringUtils.isBlank(payVo.getAddress())) {
//            data.put("msg", "请填写收货地址！");
//            data.put("code", 10000);
//            return data;
//        }
//        //校验名称和手机号码
//        if (StringUtils.isBlank(payVo.getName())) {
//            data.put("msg", "请填写名称！");
//            data.put("code", 10000);
//            return data;
//        }
//
//        if (StringUtils.isBlank(payVo.getPhone())) {
//            data.put("msg", "请填写手机号码！");
//            data.put("code", 10000);
//            return data;
//        }
//
//        if (StringUtils.isBlank(payVo.getItemName())) {
//            data.put("msg", "请选择商品！");
//            data.put("code", 10000);
//            return data;
//        }
//        BigDecimal moneyPrice = null;
//        String itemName = null;
//        if (payVo.getItemType() != null && payVo.getItemType() == 2) {
//            //校验如果是服务商，不可重复购买
//            if (isPovider(payVo.getMemberId())) {
//                data.put("msg", "您已经是服务商，无须重新购买！");
//                data.put("code", 10000);
//                return data;
//            }
//            if (payVo.getChooseCityId() == null) {
//                data.put("msg", "请选择服务商城市！");
//                data.put("code", 10000);
//                return data;
//            }
//            moneyPrice = BigDecimal.valueOf(userInfoService.findPoviderPrice(payVo.getChooseCityId()));
//            itemName = "购买海露服务商";
//            //邀请人
//            payVo.setInvitationMember(redisClient.get(RedisKit.DB_2, Constant.REDIS_INVITATION_MEMBER_POVIDER_CACHE + payVo.getMemberId()));
//        } else {
//            moneyPrice = BigDecimal.valueOf(888);
//            itemName = "购买海露会员";
//        }
//        //校验金额是否有效N
//        if (money.compareTo(moneyPrice) != 0) {
//            data.put("msg", "金额与后台计算不一致！");
//            data.put("code", 10000);
//            return data;
//        }
//        //生成订单
//        Order order = xinAnOrderService.buildOrder(payVo.getName(), payVo.getPhone(), payVo.getMemberId(), MemberShopEnum.getMap().get(payVo.getItemType()),
//                "HL", payVo.getItemName(), payVo.getInvitationMember(), money, payVo.getProvinceId(), payVo.getCityId(),
//                payVo.getAreaId(), payVo.getAddress(), payVo.getChooseCityId());
//
//        Pay pay = new Pay();
//        //会员ID
//        pay.setMemberId(payVo.getMemberId());
//        //金额
//        pay.setMoney(money);
//        //支付方式
//        pay.setPayType(payVo.getPayType() == 1 ? 1 : 2);
//        //支付订单号
//        String orderNo = NumberUtil.generateOrderSn();
//        pay.setPayOrderNo(orderNo);
//        //支付状态 1-未付款 2-已付款3-已退款4-已过期
//        pay.setPayStatus(1);
//        payVo.setOrderNo(orderNo);
//        xinAnPayService.saveEntity(pay);
//
//
//        PayExpand payExpand = new PayExpand();
//        //金额
//        payExpand.setMoney(order.getMoney());
//        //支付ID
//        payExpand.setPayId(pay.getId());
//        //支付订单号
//        payExpand.setPayOrderNo(orderNo);
//        //订单号
//        payExpand.setOrderNo(order.getOrderNo());
//
//        xinAnPayExpandService.saveEntity(payExpand);
//
//        data.put("userId", payVo.getMemberId());
//        data.put("code", 0);
//        data.put("isReserve", "1");
//        payVo.setItemName(itemName);
//        //支付参数
//        if (payVo.getPayType() == 1) {
//            data.put("payParams", CredentFactory.hLAli());
//        } else {
//            /**
//             * 海露的微信公众号和App走不同支付账号
//             */
//            if (StringUtils.isNotBlank(payVo.getOpenId())) {
//                data.put("payParams", CredentFactory.hLJsapiWecat());
//            } else {
//                data.put("payParams", CredentFactory.hLAPPWecat());
//            }
//        }
//        return data;
//    }
//
//    /**
//     * 判断是否为服务商
//     *
//     * @param userId
//     * @return
//     */
//    public boolean isPovider(String userId) {
//        UserInfo userInfo = userInfoMapper.byIdFindUser(userId);
//        if (userInfo != null && userInfo.getMerchantType() == 2) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Map<String, Object> callback(Map<String, Object> params) throws Exception {
//        Map<String, Object> result = new HashMap<>();
//        result.put("code", 1);
//        try {
//            //类型  1-支付宝2-微信
//            int payType = (int) params.get("payType");
//            //商家订单号
//            String outTradeNo = (String) params.get("outTradeNo");
//            //第三方交易号
//            String tradeNo = (String) params.get("tradeNo");
//            //交易状态
//            String tradeStatus = (String) params.get("tradeStatus");
//            //金额
//            BigDecimal money = BigDecimal.valueOf((double) params.get("money"));
//
//            //根据订单号获取订单
//            Pay pay = xinAnPayService.findByPayOrderNo(outTradeNo);
//            if (pay == null) {
//                result.put("code", 10000);
//                result.put("msg", "订单不存在");
//                log.warn("订单号：{}，订单不存在", outTradeNo);
//                return result;
//            }
//            /**修改订单状态**/
//            //判断金额是否正确
//            if (money.compareTo(pay.getMoney()) != 0) {
//                result.put("code", 10000);
//                result.put("msg", "付款金额和订单金额不一致");
//                log.warn("订单号：{}，付款金额和订单金额不一致", outTradeNo);
//                return result;
//            }
//            //支付时间
//            pay.setPayTime(System.currentTimeMillis());
//            //第三方交易号
//            pay.setTradeNo(tradeNo);
//            //付款状态 1-待付款2-已付款3-已取消
//            pay.setPayStatus(2);
//            //支付方式
//            pay.setPayType(payType);
//            xinAnPayService.saveEntity(pay);
//
//            List<PayExpand> payExpandList = xinAnPayExpandService.findListByPayId(pay.getId());
//            /**修改订单状态**/
//            for (PayExpand p : payExpandList) {
//                //获取订单信息
//                Order order = xaOrderMapper.findByOrderNo(p.getOrderNo());
//                //支付方式
//                order.setPayType(payType);
//                //将订单状态改成已支付 订单状态（1-未付款2-已付款3-已取消）
//                order.setOrderStatus(2);
//                xinAnOrderService.saveEntity(order);
//
//                //判断是否为服务商
//                if (StringUtils.equals(order.getItemType(), MemberShopEnum.PROVIDER.getDesc())) {
//                    String invitationMember = "0";
//                    //判断是否有邀请人
//                    if (StringUtils.isNotBlank(order.getInvitationMember())) {
//                        //校验邀请人的身份类型
//                        UserInfo parentUserInfo = userInfoMapper.byIdFindUser(order.getInvitationMember());
//                        if (parentUserInfo.getMerchantType() == 1) {
//                            invitationMember = order.getInvitationMember();
//                        } else {
//                            invitationMember = parentUserInfo.getSuperiorMember();
//                        }
//                        //如果金额为30000，则参与分销
//                        if (money.compareTo(BigDecimal.valueOf(30000)) == 0) {
//                            ledgerFixedRatioService.editInvitationProvider(parentUserInfo, money);
//                        }
//                    }
//                    //更改成为服务商
//                    userInfoMapper.editMerchantTypeAndSuperiorMember(order.getMemberId(), 2, invitationMember, order.getChooseCityId());
//                } else if (StringUtils.equals(order.getItemType(), MemberShopEnum.MEMBER.getDesc())) {
//                    //海露会员
//
//                    UserInfo userInfo = userInfoMapper.byIdFindUser(order.getMemberId());
//                    //校验是否为会员
//                    Date timeOut = null;
//                    if (userInfo.getHlMember() == 1) {
//                        //再过期时间的基础上再加一年
//                        timeOut = DateUtils.add(userInfo.getHlMemberTimeout(), 1, DateTimeType.YEARS);
//                    } else {
//                        timeOut = DateUtils.add(new Date(), 1, DateTimeType.YEARS);
//                    }
//                    //更改成会员
//                    userInfoService.editHlMember(order.getMemberId(), 2, RandomUtil.getRandomCode(20), timeOut);
//                    //判断是否有邀请人
//                    if (StringUtils.isNotBlank(order.getInvitationMember())) {
//                        //处理邀请人
//                        ledgerFixedRatioService.editInvitation(order.getInvitationMember());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            log.warn("处理支付订单出现错误：" + e.getMessage(), e);
//            result.put("code", 10000);
//            result.put("msg", e.getMessage());
//        }
//        return result;
//    }
}

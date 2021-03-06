package com.hailu.cloud.api.xinan.module.app.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.xinan.feigns.MallFeignClient;
import com.hailu.cloud.api.xinan.module.app.dao.OrderMapper;
import com.hailu.cloud.api.xinan.module.app.entity.*;
import com.hailu.cloud.api.xinan.module.app.service.IPaymentService;
import com.hailu.cloud.common.entity.member.ShopMember;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.PaymentFeignClient;
import com.hailu.cloud.common.model.mall.hlorder.HlOrder;
import com.hailu.cloud.common.model.payment.PayCallbackModel;
import com.hailu.cloud.common.model.payment.PayRequest;
import com.hailu.cloud.common.model.system.InvitedetailModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.response.ApiResponse;
import com.hailu.cloud.common.utils.ChinaumsUtils;
import com.hailu.cloud.common.utils.IPUtil;
import com.hailu.cloud.common.utils.ChinaumsUtils;
import com.hailu.cloud.common.utils.RequestUtils;
import com.hailu.cloud.common.utils.wechat.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service.Impl
 * @Author: junpei.deng
 * @CreateTime: 2019-10-21 17:04
 * @Description: 心安支付逻辑
 */
@Slf4j
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private OrderService xinAnOrderService;

    @Resource
    private OrderMapper xaOrderMapper;

    @Autowired
    private PayService xinAnPayService;

    @Autowired
    private PayExpandService xinAnPayExpandService;

    @Autowired
    private InsuredService xinAnInsuredService;

    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @Autowired
    private DonationService donationService;

    @Autowired
    private RedisStandAloneClient redisKit;

    @Autowired
    private MallFeignClient mallFeignClient;

    @Value("${notify.server.url}")
    private String serverUrl;

    @Value("${chinaums.key}")
    private String key;

    @Resource
    private ChinaumsUtils chinaumsUtils;

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public Map<String, Object> createOrder(Integer payType, Double moneyPrice, String insuredIds) throws BusinessException {
        Map<String, Object> data = new HashMap<>();

        //会员ID
        String userId = RequestUtils.getMemberLoginInfo().getUserId();
        //获取需要支付的参保人
        if (StringUtils.isBlank(insuredIds)) {
            throw new BusinessException("参保人不能为空！");
        }
        String[] insuredArr = insuredIds.split(",");

        //根据会员ID获取信息
        List<Order> orderVoList = new ArrayList<>();
        //金额 计算出总金额
        BigDecimal money = new BigDecimal("0");
        //根据参保人ID获取订单参数
        for (String o : insuredArr) {
            Insured insured = xinAnInsuredService.findById(o);
            if (insured == null) {
                log.info("参保ID为{}，查询不到信息！", o);
                continue;
            }
            Order order = xaOrderMapper.findByNameAndValue(insured.getName(), insured.getValue());
            money = money.add(order.getMoney());
            orderVoList.add(order);
        }
        //校验金额是否一致
        if (money.compareTo(BigDecimal.valueOf(moneyPrice)) != 0) {
            throw new BusinessException("金额与后台计算不一致！");
        }

        boolean moneyFlag = false;
        //校验金额是否为0 ,直接支付成功
        if (money.compareTo(BigDecimal.valueOf(0.00)) == 0) {
            moneyFlag = true;
        }

        Pay pay = new Pay();

        //会员ID
        pay.setMemberId(userId);
        //金额
        pay.setMoney(money);
        //支付方式
        pay.setPayType(payType == 1 ? 1 : 2);
        //支付订单号
        String orderNo = RandomUtil.randomString(25);
        pay.setPayOrderNo(orderNo);
        //支付状态 1-未付款 2-已付款3-已退款4-已过期
        pay.setPayStatus(moneyFlag ? 2 : 1);

        xinAnPayService.saveEntity(pay);

        //订单拓展表
        for (Order o : orderVoList) {
            PayExpand payExpand = new PayExpand();
            //金额
            payExpand.setMoney(o.getMoney());
            //支付ID
            payExpand.setPayId(pay.getId());
            //支付订单号
            payExpand.setPayOrderNo(orderNo);
            //订单号
            payExpand.setOrderNo(o.getOrderNo());

            xinAnPayExpandService.saveEntity(payExpand);
            if (moneyFlag) {
                //获取订单信息
                Order order = xaOrderMapper.findByOrderNo(o.getOrderNo());
                //支付方式
                order.setPayType(4);
                //将订单状态改成已支付 订单状态（1-未付款2-已付款3-已取消）
                order.setOrderStatus(2);
                xinAnOrderService.saveEntity(order);
                //修改参保人状态
                xinAnInsuredService.editInsurdPayStatus(order.getInsuredName(), order.getInsuredValue());
            }
        }
        if (moneyFlag) {
            //1代表0元，无需支付，直接成功购买
            data.put("msg", "购买成功！");
            data.put("code", 1);
            return data;
        }
        PayRequest payRequest = new PayRequest();
        //订单号
        payRequest.setOrderNo(orderNo);
        //金额
        payRequest.setMoney(money);
        //商品名称
        payRequest.setGoodsName("购买心安会员");
        //支付类型
        payRequest.setPayType(payType);
        //支付方式
        payRequest.setPayWay(1);
        //回调地址
        payRequest.setNotifyUrl(serverUrl + "/xinan/payment/callbackWechat");
        //支付参数
        payRequest.setPayParams(payType == 1 ? "XINANALI" : "XINANWECAT");
        //IP地址
        payRequest.setIp(IPUtil.getRemoteHost(RequestUtils.getRequest()));

        //调用支付服务
        return paymentFeignClient.gateway(payRequest).getData();
    }


    @Override
    public void chinaumsCallback() throws Exception {
        JSONObject params = chinaumsUtils.checkChinaumsParams(RequestUtils.getRequest(),key);
        xinAnCallBackCommon(new PayCallbackModel(params.getString("merOrderId"), params.getString("targetOrderId"),BigDecimal.valueOf(params.getInteger("totalAmount")).divide(BigDecimal.valueOf(100)),2));
    }

    @Override
    public void callback() throws Exception {
        Map<String, Object> params = WechatUtil.weCatCallback(WechatUtil.xmlToMap(RequestUtils.getRequest().getInputStream()));
        xinAnCallBackCommon(new PayCallbackModel((String) params.get("outTradeNo"),(String)params.get("tradeNo"),BigDecimal.valueOf((double) params.get("money")),2));
    }

    public void xinAnCallBackCommon(PayCallbackModel payCallbackModel) throws BusinessException {
        try {
            //类型  1-支付宝2-微信
            int payType = payCallbackModel.getPayType();

            //商家订单号
            String outTradeNo = payCallbackModel.getOrderNo();
            //第三方交易号
            String tradeNo = payCallbackModel.getTradeNo();
            //金额
            BigDecimal money = payCallbackModel.getMoney();

            //根据订单号获取订单
            Pay pay = xinAnPayService.findByPayOrderNo(outTradeNo);

            if(pay == null){
                throw new BusinessException("订单不存在");
            }

            //支付时间
            pay.setPayTime(System.currentTimeMillis());
            //第三方交易号
            pay.setTradeNo(tradeNo);
            //付款状态 1-待付款2-已付款3-已取消
            pay.setPayStatus(2);
            //支付方式
            pay.setPayType(payType);
            xinAnPayService.saveEntity(pay);

            //根据订单拓展表获取相关联的数据
            List<PayExpand> payExpandList = xinAnPayExpandService.findListByPayId(pay.getId());
            for (PayExpand p : payExpandList) {
                //获取订单信息
                Order order = xaOrderMapper.findByOrderNo(p.getOrderNo());
                //支付方式
                order.setPayType(payType);
                //将订单状态改成已支付 订单状态（1-未付款2-已付款3-已取消）
                order.setOrderStatus(2);
                xinAnOrderService.saveEntity(order);
                //修改参保人状态
                xinAnInsuredService.editInsurdPayStatus(order.getInsuredName(), order.getInsuredValue());
            }
        } catch (Exception e) {
            log.warn("处理支付订单出现错误：", e.getMessage());
            throw new BusinessException("处理支付订单出现错误");
        }
    }

    @Override
    public Map<String, Object> createHlOrder(Integer payType, double moneys, String address, String provinceId, String cityId, String areaId, String itemName, String name, String phone, Long chooseCityId, String openid,String remark,Integer buyType,String inviteNum) throws BusinessException {
        String userId = RequestUtils.getMemberLoginInfo().getUserId();
        BigDecimal money = new BigDecimal(moneys).setScale(2, BigDecimal.ROUND_HALF_UP);
        int orderType = 1;
        //校验如果是服务商，不可重复购买
        if (isPovider(userId,buyType)) {
            throw new BusinessException("您已购买，无须重新购买！");
        }

        //如果购买类型为销售 判断邀请人是否为市公司
        if(buyType == 3){
            ShopMember userInfo = mallFeignClient.findById(inviteNum).getData();
            if(userInfo.getMerchantType() != 1){
                throw new BusinessException("邀请人非市公司，无法加入销售！");
            }
            orderType= 2;
        }

        BigDecimal moneyPrice = BigDecimal.valueOf(mallFeignClient.findPoviderPrice(chooseCityId).getData());

        //校验金额是否有效N
        if (money.compareTo(moneyPrice) != 0) {
            throw new BusinessException("金额与后台计算不一致！");
        }
        Long memberDetailId  = mallFeignClient.addAddress(userId,provinceId,cityId,areaId,address,buyType,name,phone).getData();

        HlOrder hlOrder = mallFeignClient.buildOrder(userId,memberDetailId,itemName,money,remark,name,null,null,provinceId,cityId
                ,areaId,address,orderType,inviteNum,payType).getData();

        Pay pay = new Pay();
        //会员ID
        pay.setMemberId(userId);
        //金额
        pay.setMoney(money);
        //支付方式
        pay.setPayType(payType == 1 ? 1 : 2);
        //支付订单号
        String orderNo = RandomUtil.randomString(25);
        pay.setPayOrderNo(orderNo);
        //支付状态 1-未付款 2-已付款3-已退款4-已过期
        pay.setPayStatus(1);

        xinAnPayService.saveEntity(pay);



        //订单信息
        PayExpand payExpand = new PayExpand();
        //金额
        payExpand.setMoney(hlOrder.getMoney());
        //支付ID
        payExpand.setPayId(pay.getId());
        //支付订单号
        payExpand.setPayOrderNo(orderNo);
        //订单号
        payExpand.setOrderNo(hlOrder.getOrderNo());
        xinAnPayExpandService.saveEntity(payExpand);
        //请求支付参数
        PayRequest payRequest = new PayRequest();
        //订单号
        payRequest.setOrderNo(orderNo);
        //金额
        payRequest.setMoney(hlOrder.getMoney());
        //支付类型
        payRequest.setPayType(payType);
        //回调地址
        payRequest.setNotifyUrl(serverUrl + "/xinan/payment/callbackWechatHl");
        //商品名称
        payRequest.setGoodsName("购买服务商");
        //IP
        payRequest.setIp(IPUtil.getRemoteHost(RequestUtils.getRequest()));
        payRequest.setPayWay(1);
        //支付参数
        if (payType == 1) {
            payRequest.setPayParams("HLALI");
        } else {
            if (StringUtils.isNotBlank(openid)) {
                payRequest.setPayParams("HLJSAPIWECAT");
                payRequest.setPayWay(3);
                payRequest.setOpenId(openid);
            } else {
                payRequest.setPayParams("HLAPPWECAT");
            }
        }

        Map<String, Object> resultMap = paymentFeignClient.gateway(payRequest).getData();
        log.info("海露支付返回数据：{}",resultMap);
        return resultMap;
    }

    /**
     * 判断是否为服务商
     *
     * @param userId
     * @return
     */
    public boolean isPovider(String userId,Integer buyType) {
        ApiResponse<ShopMember> userInfo = mallFeignClient.findById(userId);
        if (userInfo.getData() != null && userInfo.getData().getMerchantType() == buyType) {
            return true;
        }
        return false;
    }

    @Override
    public void chinaumsCallbackHl() throws Exception {
        JSONObject params = chinaumsUtils.checkChinaumsParams(RequestUtils.getRequest(),key);
        callbackHlCommon(new PayCallbackModel(params.getString("merOrderId"), params.getString("targetOrderId"),BigDecimal.valueOf(params.getInteger("totalAmount")).divide(BigDecimal.valueOf(100)),2));
    }

    @Override
    public void callbackHl() throws Exception {
        Map<String, Object> params = WechatUtil.weCatCallback(WechatUtil.xmlToMap(RequestUtils.getRequest().getInputStream()));
            callbackHlCommon(new PayCallbackModel((String) params.get("outTradeNo"),(String)params.get("tradeNo"),BigDecimal.valueOf((double) params.get("money")),2));
    }

    public void callbackHlCommon(PayCallbackModel payCallbackModel) throws BusinessException {
        try {
            //类型  1-支付宝2-微信
            int payType = payCallbackModel.getPayType();
            //商家订单号
            String outTradeNo = payCallbackModel.getOrderNo();
            //第三方交易号
            String tradeNo = payCallbackModel.getTradeNo();
            //金额
            BigDecimal money = payCallbackModel.getMoney();

            //根据订单号获取订单
            Pay pay = xinAnPayService.findByPayOrderNo(outTradeNo);
            if (pay == null) {
                log.warn("订单号：{}，订单不存在", outTradeNo);
                throw new BusinessException("订单不存在");
            }
            /**修改订单状态**/
            //判断金额是否正确
            if (money.compareTo(pay.getMoney()) != 0) {
                log.warn("订单号：{}，付款金额和订单金额不一致", outTradeNo);
                throw new BusinessException("付款金额和订单金额不一致");
            }
            //支付时间
            pay.setPayTime(System.currentTimeMillis());
            //第三方交易号
            pay.setTradeNo(tradeNo);
            //付款状态 1-待付款2-已付款3-已取消
            pay.setPayStatus(2);
            //支付方式
            pay.setPayType(payType);
            xinAnPayService.saveEntity(pay);

            //将服务商信息里面状态修改成服务商  是否服务商（1-是、2-否）
            mallFeignClient.updateStatusByUserId(pay.getMemberId(), 1);

            List<PayExpand> payExpandList = xinAnPayExpandService.findListByPayId(pay.getId());
            /**修改订单状态**/
            for (PayExpand p : payExpandList) {
                //获取订单信息
                HlOrder order = mallFeignClient.findByOrderNo(p.getOrderNo()).getData();
                //将订单状态改成已支付 订单状态（1-未付款2-已付款3-已取消）
                order.setOrderStatus(2);
                mallFeignClient.saveHlOrder(order);

                String invitationMember = "0";
                //判断是否有邀请人
                if (StringUtils.isNotBlank(order.getInvitationMember())) {

                    //生成邀请信息
                    InvitedetailModel invitedetailModel = new InvitedetailModel();
                    invitedetailModel.setInvitationId(order.getInvitationMember());
                    invitedetailModel.setBeInvitedId(order.getUserId());
                    invitedetailModel.setType(order.getOrderType() == 1 ? 2 : 3);

                    mallFeignClient.addInvitedetail(invitedetailModel);

                    if(order.getOrderType() == 1){
                        //校验邀请人的身份类型
                        ShopMember parentUserInfo = mallFeignClient.findById(order.getInvitationMember()).getData();
                        if (parentUserInfo.getMerchantType() == 1) {
                            invitationMember = order.getInvitationMember();
                        } else {
                            invitationMember = parentUserInfo.getSuperiorMember();
                        }
                        //如果金额为30000，则参与分销
                        if (money.compareTo(BigDecimal.valueOf(30000)) == 0) {
                            mallFeignClient.editInvitationProvider(parentUserInfo, money);
                        }
                        //更改成为服务商
                        mallFeignClient.editMerchantTypeAndSuperiorMember(order.getUserId(), 2, invitationMember, Long.parseLong(mallFeignClient.findDetail(order.getUserId()).getData().getCityId()));
                    }else if(order.getOrderType() == 2){
                        //更改成为服务商
                        mallFeignClient.editMerchantTypeAndSuperiorMember(order.getUserId(), 3, invitationMember, Long.parseLong(mallFeignClient.findDetail(order.getUserId()).getData().getCityId()));
                    }

                }

            }
        } catch (Exception e) {
            log.warn("处理支付订单出现错误：" + e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> donationOrder(Integer payType, Double moneyPrice, String orderId) throws BusinessException {
        Map<String, Object> data = new HashMap<>();

        //会员ID
        String userId = RequestUtils.getMemberLoginInfo().getUserId();
        //获取需要支付的参保人
        if (StringUtils.isBlank(orderId)) {
            throw new BusinessException("参保人不能为空！");
        }

        //金额 计算出总金额
        BigDecimal money = new BigDecimal("0");

        Order order = xaOrderMapper.selectByPrimaryKey(orderId);
        money = money.add(order.getMoney());
        //校验金额是否一致
        if (money.compareTo(BigDecimal.valueOf(moneyPrice)) != 0) {
            throw new BusinessException("金额与后台计算不一致！");
        }

        boolean moneyFlag = false;
        //校验金额是否为0 ,直接支付成功
        if (money.compareTo(BigDecimal.valueOf(0.00)) == 0) {
            moneyFlag = true;
        }

        Pay pay = new Pay();

        //会员ID
        pay.setMemberId(userId);
        //金额
        pay.setMoney(money);
        //支付方式
        pay.setPayType(payType == 1 ? 1 : 2);
        //支付订单号
        String orderNo = IdUtil.simpleUUID();
        pay.setPayOrderNo(orderNo);
        //支付状态 1-未付款 2-已付款3-已退款4-已过期
        pay.setPayStatus(moneyFlag ? 2 : 1);

        xinAnPayService.saveEntity(pay);

        if (moneyFlag) {
            //1代表0元，无需支付，直接成功购买
            data.put("msg", "捐赠成功！");
            data.put("code", 1);
            return data;
        }
        PayRequest payRequest = new PayRequest();
        //订单号
        payRequest.setOrderNo(orderNo);
        //金额
        payRequest.setMoney(money);
        //商品名称
        payRequest.setGoodsName("捐赠");
        //支付类型
        payRequest.setPayType(payType);
        //支付方式
        payRequest.setPayWay(1);
        //回调地址
        payRequest.setNotifyUrl(serverUrl + "/xinan/payment/callbackWeChatDonation");
        //支付参数
        payRequest.setPayParams(payType == 1 ? "XINANALI" : "XINANWECAT");
        //IP地址
        payRequest.setIp(IPUtil.getRemoteHost(RequestUtils.getRequest()));

        //调用支付服务
        return paymentFeignClient.gateway(payRequest).getData();
    }

    @Override
    public void chinaumsCallbackDonation() throws Exception {
        JSONObject params = chinaumsUtils.checkChinaumsParams(RequestUtils.getRequest(),key);
        callbackDonationCommon(new PayCallbackModel(params.getString("merOrderId"), params.getString("targetOrderId"),BigDecimal.valueOf(params.getInteger("totalAmount")).divide(BigDecimal.valueOf(100)),2));
    }

    @Override
    public void callbackDonation() throws Exception {
        Map<String, Object> params = WechatUtil.weCatCallback(WechatUtil.xmlToMap(RequestUtils.getRequest().getInputStream()));
        callbackDonationCommon(new PayCallbackModel((String) params.get("outTradeNo"),(String)params.get("tradeNo"),BigDecimal.valueOf((double) params.get("money")),2));
    }

    public void callbackDonationCommon(PayCallbackModel payCallbackModel) throws BusinessException {
        try {
            //类型  1-支付宝2-微信
            int payType = payCallbackModel.getPayType();

            //商家订单号
            String outTradeNo = payCallbackModel.getOrderNo();
            //第三方交易号
            String tradeNo = payCallbackModel.getTradeNo();
//            //金额
            BigDecimal money = payCallbackModel.getMoney();

            //根据订单号获取订单
            Pay pay = xinAnPayService.findByPayOrderNo(outTradeNo);

            if (pay == null) {
                throw new BusinessException("订单不存在");
            }

            //判断金额是否正确
            if (money.compareTo(pay.getMoney()) != 0) {
                log.warn("订单号：{}，付款金额和订单金额不一致", outTradeNo);
                throw new BusinessException("付款金额和订单金额不一致");
            }
            //支付时间
            pay.setPayTime(System.currentTimeMillis());
            //第三方交易号
            pay.setTradeNo(tradeNo);
            //付款状态 1-待付款2-已付款3-已取消
            pay.setPayStatus(2);
            //支付方式
            pay.setPayType(payType);
            xinAnPayService.saveEntity(pay);

            //获取订单信息
            Order order = xaOrderMapper.findByOrderNo(outTradeNo);
            //支付方式
            order.setPayType(payType);
            //将订单状态改成已支付 订单状态（1-未付款2-已付款3-已取消）
            order.setOrderStatus(2);
            xinAnOrderService.saveEntity(order);

            Donation donation = new Donation();
            //要捐赠的表编号
            donation.setDonation(order.getRescueId());

            //捐赠类型
            switch (order.getItemType()) {
                case "GOVERNMENT_CHARITY":
                    donation.setDonationType(1);
                    break;
                case "RESCUE":
                    donation.setDonationType(2);
                    break;
                case "MUTUAL_AID":
                    donation.setDonationType(3);
                    break;
                default:
                    break;
            }
            //捐赠金额
            donation.setDonationMoney(money);
            donationService.insOrderDonation(donation);

        } catch (Exception e) {
            log.warn("处理支付订单出现错误：", e.getMessage());
            throw new BusinessException("处理支付订单出现错误");
        }
    }

}

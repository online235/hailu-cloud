package com.hailu.cloud.api.mall.module.goods.service.impl;


import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.mall.module.goods.dao.OrderMapper;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;
import com.hailu.cloud.api.mall.module.goods.service.IOSSOrderService;
import com.hailu.cloud.api.mall.module.goods.service.IOrderService;
import com.hailu.cloud.api.mall.module.goods.vo.vm.OrderGoodsVm;
import com.hailu.cloud.api.mall.module.ledger.service.impl.LedgerFixedRatioServiceImpl;
import com.hailu.cloud.api.mall.module.payment.vo.OrderPay;
import com.hailu.cloud.api.mall.module.payment.vo.OrderToPay;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.PaymentFeignClient;
import com.hailu.cloud.common.model.payment.PayCallbackModel;
import com.hailu.cloud.common.model.payment.PayRequest;
import com.hailu.cloud.common.utils.IPUtil;
import com.hailu.cloud.common.utils.ChinaumsUtils;
import com.hailu.cloud.common.utils.RequestUtils;
import com.hailu.cloud.common.utils.wechat.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方支付
 *
 * @author Administrator
 */
@Slf4j
@Service
public class OSSOrderServiceImpl implements IOSSOrderService {

    @Autowired
    private IOrderService orderService;

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @Value("${notify.server.url}")
    private String serverUrl;

    @Autowired
    private LedgerFixedRatioServiceImpl ledgerFixedRatioService;

    @Value("${chinaums.key}")
    private String key;

    @Resource
    private ChinaumsUtils chinaumsUtils;

    public void callbackCommon(PayCallbackModel payCallbackModel) throws BusinessException {
        try {
        //类型  1-支付宝2-微信
        int payType = payCallbackModel.getPayType();

        //商家订单号
        String outTradeNo = payCallbackModel.getOrderNo();
        outTradeNo = outTradeNo.split("-")[0];
        //第三方交易号
        String tradeNo = payCallbackModel.getTradeNo();
        //金额
        BigDecimal money = payCallbackModel.getMoney();


        OrderToVo ol = orderService.findByOrderSn(outTradeNo);
        if (ol.getOrderState() < 3) {
            // 直接下單
            if (ol != null) {
                if (ol.getOrderState() == 1) {
                    log.info("订单： " + outTradeNo + "。进入了支付回调，支付状态为:" + ol.getPaymentState() + "----订单状态为:"
                            + ol.getOrderState());
                    OrderPay op = new OrderPay();
                    op.setApiPayState(1);
                    op.setBuyerId(ol.getUserId());
                    op.setPaySn(tradeNo);
                    op.setOrderSn(outTradeNo);
                    op.setPayTime(System.currentTimeMillis());
                    op.setPayType(payType);
                    op.setWechatSource("APP");
                    if (ol.getPaymentState() == 0) {
                        OrderToPay otp = new OrderToPay();
                        otp.setOrderId(ol.getOrderId());
                        otp.setPaymentName(payType == 1? "支付宝":"微信");
                        otp.setPaymentState(1);
                        otp.setPaymentTime(System.currentTimeMillis());
                        // 是否預定
                        if (ol.getIsReserve() == 1) {
                            // 第一階段是否支付
                            if (ol.getOneIsPay() == 0 && money.equals(new BigDecimal(ol.getReserveOneAmount()))) {
                                otp.setOneIsPay(1);
                                otp.setPaymentState(0);// 是否支付
                                op.setPayAmount(ol.getReserveOneAmount());
                            } else if(money.equals(new BigDecimal(ol.getReserveTwoAmount()))){
                                otp.setTwoIsPay(1);
                                otp.setOrderState(2);
                                op.setPayAmount(ol.getReserveTwoAmount());
                            }else {
                                return;
                            }
                        } else {
                            otp.setOrderState(2);
                            op.setPayAmount(ol.getOrderAmount());
                        }
                        orderService.updateOrderByPay(otp); // 更改訂單
                        orderService.addOrderPay(op); // 支付詳情

                        log.info("支付成功");
                    }
                }
            }
            //判断该商品是否是可分享商品
            //判断刚订单是否被分享
            //处理分销
            ledgerFixedRatioService.distributionGoods(ol.getOrderId(),ol.getUserId());
        }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void chinaumsCallback() throws BusinessException {
        JSONObject params = chinaumsUtils.checkChinaumsParams(RequestUtils.getRequest(),key);
        callbackCommon(new PayCallbackModel(params.getString("merOrderId"), params.getString("targetOrderId"),BigDecimal.valueOf(params.getInteger("totalAmount")).divide(BigDecimal.valueOf(100)),2));
    }

    @Override
    public void callback() throws Exception {
        Map<String, Object> params = WechatUtil.weCatCallback(WechatUtil.xmlToMap(RequestUtils.getRequest().getInputStream()));
        callbackCommon(new PayCallbackModel((String) params.get("outTradeNo"),(String)params.get("tradeNo"),BigDecimal.valueOf((double) params.get("money")),2));
    }

    @Override
    public Map<String, Object> createOrder(String orderSn, Integer payType, String openId,String returnUrl) throws BusinessException {

        PayRequest payRequest = new PayRequest();
        log.info("订单支付|payType={}|orderNumber={}",payType,orderSn);
        Map<String, Object> data = new HashMap<>(10);
        //获取根据订单编号得到订单的信息
        OrderToVo ol = orderService.findByOrderSn(orderSn);
        if (ol != null) {
            List<OrderGoodsVm> orderGoodsList = orderMapper.findOrderVmByOrderId(ol.getOrderId());
            StringBuffer itemNameSb = new StringBuffer();
            for(OrderGoodsVm o : orderGoodsList){
                itemNameSb.append(o.getGoodsName()+",");
            }
            String itemName = itemNameSb.toString();
            payRequest.setGoodsName("购买商品："+itemName.substring(0,itemName.length()-1));
            //订单取金额逻辑 , 如果是预定2
            double orderAmount;
            String isReserve = "0";
            if (ol.getIsReserve() == 1) {
                //第一阶段支付了,取第二阶段的金额
                if (ol.getOneIsPay() == 1) {
                    orderSn = orderSn + "s";
                    orderAmount = ol.getReserveTwoAmount();
                    isReserve = "2";
                } else {
                    orderAmount = ol.getReserveOneAmount();
                    isReserve = "1";
                }
            } else {
                orderAmount = ol.getOrderAmount();
            }
            data.put("isReserve",isReserve);
            if (orderAmount <= 0) {
                addZeroPay(ol);
                //金额为0的订单
                data.put("code",10);
                return data;
            }

            //金额
            payRequest.setMoney(BigDecimal.valueOf(orderAmount));
            //支付类型
            payRequest.setPayType(payType);
            //支付方式
            payRequest.setPayWay(1);
            //支付参数
            if(payType == 1){
                payRequest.setPayParams("HLALI");
            }else {
                /**
                 * 海露的微信公众号和App走不同支付账号
                 */
                if(StringUtils.isNotBlank(openId)){
                    payRequest.setPayWay(3);
                    payRequest.setOpenId(openId);
                    payRequest.setPayParams("HLJSAPIWECAT");
                }else {
                    payRequest.setPayParams("HLAPPWECAT");
                }
            }
            //订单号   因为订单支付确认订单的时候就生成，当客户第一次去支付而取消，导致第二次会因为订单号重复失败，所以在后面加了三位随机数
            payRequest.setOrderNo(orderSn + "-" + RandomUtil.randomNumbers(3));
            //IP地址
            payRequest.setIp(IPUtil.getRemoteHost(RequestUtils.getRequest()));
            //回调地址
            payRequest.setNotifyUrl(serverUrl+"/mall/payment/callback/weixin");
            if(StringUtils.isNotBlank(returnUrl)){
                payRequest.setReturnUrl(returnUrl);
            }
        } else {
            throw new BusinessException("不能重复支付");
        }

        return paymentFeignClient.gateway(payRequest).getData();
    }


    @Override
    public void addZeroPay(OrderToVo ol) {
        try {
            if (ol.getOrderState() == 1) {
                OrderPay op = new OrderPay();
                op.setApiPayState(1);
                op.setBuyerId(ol.getUserId());
                op.setPaySn(RandomStringUtils.randomAlphanumeric(15));
                op.setOrderSn(ol.getOrderSn());
                op.setPayTime(System.currentTimeMillis());
                op.setPayType(3);
                if (ol.getIsReserve() == 1) {
                    //第一阶段支付了,取第二阶段的金额
                    if (ol.getOneIsPay() == 1) {
                        op.setPayAmount(ol.getReserveTwoAmount());
                    } else {
                        op.setPayAmount(ol.getReserveOneAmount());
                    }
                } else {
                    op.setPayAmount(ol.getOrderAmount());
                }
                orderService.addOrderPay(op);
                if (ol.getPaymentState() == 0) {
                    OrderToPay otp = new OrderToPay();
                    otp.setOrderId(ol.getOrderId());
                    otp.setPaymentName("余额");
                    otp.setPaymentState(1);
                    otp.setPaymentTime(System.currentTimeMillis());
                    if (ol.getIsReserve() == 1) {
                        //第一階段是否支付
                        if (ol.getOneIsPay() == 0) {
                            otp.setOneIsPay(1);
                            otp.setPaymentState(0);
                        } else {
                            //第二階段支付
                            otp.setTwoIsPay(1);
                            otp.setOrderState(2);
                        }
                    } else {
                        otp.setOrderState(2);
                    }
                    orderService.updateOrderByPay(otp);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}

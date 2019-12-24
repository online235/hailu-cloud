package com.hailu.cloud.api.payment.module.pay.service;

import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.payment.PayRequest;

import java.io.IOException;
import java.util.Map;

/**
 * 支付抽象类
 */
public abstract class AbstractPayService implements IPayService {

    /**
     * 微信预付款订单地址
     */
    public static final String WEIXIN_UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String ALI_GATEWAY_URL = "https://openapi.alipay.com/gateway.do";

    public Map<String,Object> pay(PayRequest payRequest) throws BusinessException, IOException {
        if(payRequest.getPayType() == 1){
            return this.payForAli(payRequest);
        }else {
            return this.payForWeCat(payRequest);
        }
    }



}

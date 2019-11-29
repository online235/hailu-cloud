package com.hailu.cloud.api.payment.module.pay.service;

import com.hailu.cloud.common.model.payment.PayRequest;

import java.util.Map;

public interface IPayService {

    /**
     * 微信支付
     * @param payRequest
     * @return
     */
    Map<String, Object> payForWeCat(PayRequest payRequest);

    /**
     * 支付宝支付
     * @param payRequest
     * @return
     */
    Map<String, Object> payForAli(PayRequest payRequest);
}

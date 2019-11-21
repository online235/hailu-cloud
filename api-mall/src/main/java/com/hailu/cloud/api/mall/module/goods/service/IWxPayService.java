package com.hailu.cloud.api.mall.module.goods.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信支付信息
 */
public interface IWxPayService {
    /**
     * 获取支付参数
     *
     * @param orderid 订单id
     * @param request
     * @return
     */
    Map<String, String> pay(String orderid, HttpServletRequest request);

    /**
     * 支付后回调函数
     *
     * @param orderNumber 订单编号
     * @return
     */
    boolean payNotify(String orderNumber);
}

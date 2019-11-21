package com.hailu.cloud.api.mall.module.goods.service;


import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 第三方支付
 */
public interface IOSSOrderService {
    //微信生成预付订单 并返回 sign prepay_id
    Map<String, Object> unifiedorder(Map parameterJSON, Map<String, String> payParams);

    //微信h5生成预付订单 并返回 sign prepay_id
    Map<String, Object> gzunifiedorder(Map parameterJSON, Map<String, String> payParams);

    //生成微信签名
    String weixinSign(LinkedHashMap parameterMap);

    //支付宝生成预付订单 并返回 sign prepay_id
    String aliPayUnifiedorder(HttpServletRequest httpRequest, Map<String, String> parameterMap, Map<String, String> payParams) throws Exception;

    boolean verify(Map<String, String> params);

    /**
     * 本系统的余额支付
     *
     * @param orderSn
     * @param payPwd
     * @return
     */
    Boolean addBalancePay(String orderSn, String payPwd) throws Exception;

    /**
     * @Author HuangL
     * @Description 得到微信签名发送验证
     * @Date 2018-10-30_09:16
     */
    Map<String, Object> sendUrlWeChat(String url);

    /**
     * @Author HuangL
     * @Email huangl96@163.com
     * @Description 当支付金额为0时调用
     * @Date 2018/11/28 14:49
     */
    void addZeroPay(OrderToVo ol);

}

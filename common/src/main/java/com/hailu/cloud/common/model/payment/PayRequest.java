package com.hailu.cloud.common.model.payment;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付参数
 *
 * @Author junpei.deng
 * @Date 2019/11/19 10:24
 */
@Data
public class PayRequest {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 支付类型 1-支付宝、2-微信
     */
    private Integer payType;

    /**
     * 支付方式 1-APP、2-H5、3-JSAPI
     */
    private Integer payWay;

    /**
     * 支付来源
     */
    private String payFrom;

    /**
     * 微信支付Openid
     */
    private String openId;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 描述
     */
    private String body;

    /**
     * 支付参数
     */
    private String payParams;

    /**
     * 调取支付的终端IP
     */
    private String ip;
}

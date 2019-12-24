package com.hailu.cloud.api.xinan.module.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PayExpand implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 支付ID
     */
    private String payId;

    /**
     * 支付订单号
     */
    private String payOrderNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * xa_pay_expand
     */
    private static final long serialVersionUID = 1L;
}
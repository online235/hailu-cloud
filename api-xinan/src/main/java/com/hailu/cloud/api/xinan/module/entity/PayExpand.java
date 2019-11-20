package com.hailu.cloud.api.xinan.module.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 支付ID
     * @return pay_id 支付ID
     */
    public String getPayId() {
        return payId;
    }

    /**
     * 支付ID
     * @param payId 支付ID
     */
    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    /**
     * 支付订单号
     * @return pay_order_no 支付订单号
     */
    public String getPayOrderNo() {
        return payOrderNo;
    }

    /**
     * 支付订单号
     * @param payOrderNo 支付订单号
     */
    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo == null ? null : payOrderNo.trim();
    }

    /**
     * 订单号
     * @return order_no 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 订单号
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 金额
     * @return money 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 金额
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
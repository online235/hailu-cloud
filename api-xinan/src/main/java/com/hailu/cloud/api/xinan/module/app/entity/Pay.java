package com.hailu.cloud.api.xinan.module.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Pay implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 支付订单表
     */
    private String payOrderNo;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 第三方交易号
     */
    private String tradeNo;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 支付状态 1-待付款2-已付款3-已取消
     */
    private Integer payStatus;

    /**
     * 支付时间
     */
    private Long payTime;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * x修改人
     */
    private Date updateDate;

    /**
     * x修改时间
     */
    private String updateBy;

    /**
     * xa_pay
     */
    private static final long serialVersionUID = 1L;

    /**
     * 状态（1-正常2-删除）
     */
    private Integer status;
}
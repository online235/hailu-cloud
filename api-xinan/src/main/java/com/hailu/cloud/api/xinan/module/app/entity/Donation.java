package com.hailu.cloud.api.xinan.module.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Donation implements Serializable {
    /**
     * 编号
     */
    private Long numberId;

    /**
     * 捐助金额
     */
    private BigDecimal donationMoney;

    /**
     * 捐助用户
     */
    private String memberId;

    /**
     * 捐赠类型(政府慈善-1、救助-2、互助-3)
     */
    private Integer donationType;

    /**
     * 捐赠表ID
     */
    private String donation;

    /**
     * 创建时间
     */
    private Date createdat;

    /**
     * xa_donation
     */
    private static final long serialVersionUID = 1L;
}
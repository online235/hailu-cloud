package com.hailu.cloud.api.xinan.module.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Donation implements Serializable {
    /**
     * 编号
     */
    private String numberid;

    /**
     * 捐助金额
     */
    private BigDecimal donation;

    /**
     * 捐助用户
     */
    private String memberid;

    /**
     * 互助表ID
     */
    private String mutalaid;

    /**
     * 创建时间
     */
    private Long createdat;

    /**
     * xa_donation
     */
    private static final long serialVersionUID = 1L;
}
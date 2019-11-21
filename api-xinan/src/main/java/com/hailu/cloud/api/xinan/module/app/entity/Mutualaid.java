package com.hailu.cloud.api.xinan.module.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表实体类
 * @Date: 18:17 2019/11/12 0012
 */
@Data
public class Mutualaid implements Serializable {
    /**
     * ID编号
     */
    private String numberId;

    /**
     * 发起用户ID
     */
    private String memberId;

    /**
     * 目标金额
     */
    private BigDecimal targetAmount;

    /**
     * 筹款标题
     */
    private String title;

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 医院收款账号
     */
    private String hospitalAccount;

    /**
     * 确诊病名
     */
    private String diseaseName;

    /**
     * 救助类型
     */
    private String rescueType;

    /**
     * 现金额
     */
    private BigDecimal cash;

    /**
     * 省份
     */
    private Long provinceId;

    /**
     * 城市
     */
    private Long cityId;

    /**
     * 审核
     */
    private String examine;

    /**
     * 创建时间
     */
    private Date createdat;

    /**
     * 更新时间
     */
    private Date updatedat;

    /**
     * 救助说明
     */
    private String explain;

    /**
     * 帮助次数
     */
    private byte[] helpTimes;

    /**
     * xa_mutualaid
     */
    private static final long serialVersionUID = 1L;
}
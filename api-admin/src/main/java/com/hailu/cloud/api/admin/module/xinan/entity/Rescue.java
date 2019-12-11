package com.hailu.cloud.api.admin.module.xinan.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表实体类
 * @Date: 18:17 2019/11/12 0012
 */
@Data
public class Rescue implements Serializable {
    /**
     * 编号
     */
    private Long numberId;

    /**
     * 发起用户编号
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
     * 帮助次数
     */
    private Long helpTimes;

    /**
     * 现金额
     */
    private BigDecimal cash;

    /**
     * 救助类型
     */
    private String rescueType;

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
     * 说明
     */
    private String instructions;

    /**
     * xa_rescue
     */
    private static final long serialVersionUID = 1L;
}
package com.hailu.cloud.api.admin.module.xinan.entity;

import com.hailu.cloud.common.fill.annotation.DictName;
import io.swagger.annotations.ApiModelProperty;
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
public class MutualAid implements Serializable {
    /**
     * ID编号
     */
    private Long numberId;

    /**
     * 发起用户ID
     */
    @ApiModelProperty(value = "发起用户ID")
    private String memberId;

    /**
     * 目标金额
     */
    @ApiModelProperty(value = "目标金额")
    private BigDecimal targetAmount;

    /**
     * 筹款标题
     */
    @ApiModelProperty(value = "筹款标题")
    private String title;

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    /**
     * 医院收款账号
     */
    @ApiModelProperty(value = "医院收款账号")
    private String hospitalAccount;

    /**
     * 确诊病名
     */
    @ApiModelProperty(value = "确诊病名")
    private String diseaseName;

    /**
     * 救助类型 （助学-1,助残-2,助老-3,疾病-4,扶贫-5,公益-6,救灾-7 ,医疗-8,就业-9,自然-10）
     */
    @ApiModelProperty(value = "救助类型 （助学-1,助残-2,助老-3,疾病-4,扶贫-5,公益-6,救灾-7 ,医疗-8,就业-9,自然-10）")
    private Integer rescueType;

    /**
     * 现金额
     */
    @ApiModelProperty(value = "现金额")
    private BigDecimal cash;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private Long provinceId;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private Long cityId;

    /**
     * 审核(审核中-1、审核通过-2、审核不通过-3)
     */
    @ApiModelProperty(value = "审核(审核中-1、审核通过-2、审核不通过-3)")
    private Integer toExamine;

    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    @ApiModelProperty(value = "审核")
    private String examineDisPlay;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdat;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedat;

    /**
     * 救助说明
     */
    @ApiModelProperty(value = "救助说明")
    private String explain;

    /**
     * 帮助次数
     */
    @ApiModelProperty(value = "帮助次数")
    private Long helpTimes;

    /**
     * xa_mutualaid
     */
    private static final long serialVersionUID = 1L;
}
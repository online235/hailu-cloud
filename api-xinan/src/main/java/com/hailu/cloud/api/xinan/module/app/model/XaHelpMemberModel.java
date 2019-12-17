package com.hailu.cloud.api.xinan.module.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 受帮助成员表
 *
 * @author zhijie
 */
@Data
@ApiModel
public class XaHelpMemberModel {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 省份
     */
    @ApiModelProperty("省份")
    private String province;

    /**
     * 省份code值
     */
    @ApiModelProperty("省份code值")
    private String provinceCode;

    /**
     * 病名
     */
    @ApiModelProperty("病名")
    private String disease;

    /**
     * 本期时间（yyyy-MM）
     */
    @ApiModelProperty("本期时间（yyyy-MM）")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date timeDate;

    /**
     * 期数
     */
    @ApiModelProperty("期数")
    private Integer periodsNumber;

    /**
     * 分摊次数
     */
    @ApiModelProperty("分摊次数")
    private Integer shareTimes;

    /**
     * 互助天数
     */
    @ApiModelProperty("互助天数")
    private Integer helpDays;

    /**
     * 性别：1男；2女
     */
    @ApiModelProperty("性别：1男；2女")
    private Integer sex;

    /**
     * 获取金额
     */
    @ApiModelProperty("获取金额")
    private BigDecimal gainMoney;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String imageUrl;

    /**
     * 已经分摊金钱
     */
    @ApiModelProperty("已经分摊金钱")
    private BigDecimal apportionmentMoney;


}

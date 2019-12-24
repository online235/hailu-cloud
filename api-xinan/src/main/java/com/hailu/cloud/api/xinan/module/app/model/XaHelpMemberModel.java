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
     * 发起用户id
     */
    @ApiModelProperty("发起用户id")
    private Long menberId;

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
     * 城市
     */
    @ApiModelProperty("城市")
    private String cityCode;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;

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
     * 图片
     */
    @ApiModelProperty("图片")
    private String imageUrl;

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
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 已经分摊金钱
     */
    @ApiModelProperty("已经分摊金钱")
    private BigDecimal apportionmentMoney;

    /**
     * 目标金额
     */
    @ApiModelProperty("目标金额")
    private BigDecimal targetAmount;

    /**
     * 筹款标题
     */
    @ApiModelProperty("筹款标题")
    private String title;

    /**
     * 救助说明
     */
    @ApiModelProperty("救助说明")
    private String content;

    /**
     * 医院名字
     */
    @ApiModelProperty("医院名字")
    private String hospitalName;

    /**
     * 医院收款账号
     */
    @ApiModelProperty("医院收款账号")
    private String hospitalAccount;

    /**
     * 确诊病名
     */
    @ApiModelProperty("确诊病名")
    private String diseaseName;

    /**
     * 救助类型 （助学-1,助残-2,助老-3,疾病-4,扶贫-5,公益-6,救灾-7 ,医疗-8,就业-9,自然-10）
     */
    @ApiModelProperty("救助类型 （助学-1,助残-2,助老-3,疾病-4,扶贫-5,公益-6,救灾-7 ,医疗-8,就业-9,自然-10）")
    private Integer rescueType;

    /**
     * 帮助次数
     */
    @ApiModelProperty("帮助次数")
    private Integer helpTimes;

    /**
     * 现金额
     */
    @ApiModelProperty("现金额")
    private BigDecimal cash;

    /**
     * 审核(审核中-1、审核通过-2、审核不通过-3)
     */
    @ApiModelProperty("审核(审核中-1、审核通过-2、审核不通过-3)")
    private Integer toExamine;

    @ApiModelProperty("发起人昵称")
    private String nickName;


    @ApiModelProperty("发起人头像")
    private String headImage;

}

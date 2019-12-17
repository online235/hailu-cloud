package com.hailu.cloud.api.admin.module.xinan.parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 受帮助成员表
 *
 * @author zhijie
 */
@Data
@ApiModel
public class XaHelpMemberParameter {


    /**
     * 姓名
     */
    @ApiModelProperty(name = "name", value = " 姓名",required = true)
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty(name="age",value = " 年龄",required = true)
    private Integer age;

    /**
     * 省份
     */
    @ApiModelProperty(name="province",value = " 省份",required = true)
    private String province;

    /**
     * 省份code值
     */
    @ApiModelProperty(name="provinceCode",value = " 省份code值")
    private String provinceCode;

    /**
     * 病名
     */
    @ApiModelProperty(name="disease",value = " 病名",required = true)
    private String disease;

    /**
     * 本期时间（yyyy-MM）
     */
    @ApiModelProperty(name="timeDate",value = " 本期时间（yyyy-MM-dd）",required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date timeDate;

    /**
     * 期数
     */
    @ApiModelProperty(name="periodsNumber",value = "期数",required = true)
    private Integer periodsNumber;

    /**
     * 分摊次数
     */
    @ApiModelProperty(name="shareTimes",value = " 分摊次数",required = true)
    private Integer shareTimes;

    /**
     * 互助天数
     */
    @ApiModelProperty(name="helpDays",value = " 互助天数",required = true)
    private Integer helpDays;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String imageUrl;
    

    /**
     * 性别：1男；2女
     */
    @ApiModelProperty(name="sex" , value = "性别：1男；2女",required = true)
    private Integer sex;

    /**
     * 获取金额
     */
    @ApiModelProperty(name="gainMoney",value = " 获取金额",required = true)
    private BigDecimal gainMoney;


    /**
     * 已经分摊金钱
     */
    @ApiModelProperty("已经分摊金钱")
    private BigDecimal apportionmentMoney;


}

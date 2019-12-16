package com.hailu.cloud.api.admin.module.xinan.model;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.util.Date;
import java.math.BigDecimal;

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
     * 图片
     */
    @ApiModelProperty("图片")
    private String imageUrl;

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

}

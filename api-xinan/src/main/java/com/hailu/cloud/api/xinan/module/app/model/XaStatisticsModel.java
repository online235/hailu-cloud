package com.hailu.cloud.api.xinan.module.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 心安期数统计
 *
 * @author zhijie
 */
@Data
@ApiModel
public class XaStatisticsModel {

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 帮助会员（单位：人）
     */
    @ApiModelProperty("帮助会员（单位：人）")
    private Integer helpMenber;

    /**
     * 预筹集互助金（单位：万元）
     */
    @ApiModelProperty("预筹集互助金（单位：万元）")
    private BigDecimal preMutualCapital;

    /**
     * 分摊人数
     */
    @ApiModelProperty("分摊人数")
    private BigDecimal apportionmentNum;

    /**
     * 全员每人预分摊
     */
    @ApiModelProperty("全员每人预分摊")
    private BigDecimal averageMoney;

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


    @ApiModelProperty("案例")
    private List<XaHelpMemberModel> xaHelpMemberModelList;



}

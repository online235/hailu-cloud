package com.hailu.cloud.api.admin.module.xinan.parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 心安期数统计
 *
 * @author zhijie
 */
@Data
@ApiModel
public class XaStatisticsParameterUpdate {

    /**
     * id
     */
    @ApiModelProperty(name="id",value = "id",required = true)
    private Long id;

    /**
     * 帮助会员（单位：人）
     */
    @ApiModelProperty(name="helpMenber",value = "帮助会员（单位：人）")
    private Integer helpMenber;

    /**
     * 预筹集互助金（单位：万元）
     */
    @ApiModelProperty(name="preMutualCapital",value = " 预筹集互助金（单位：万元）")
    private BigDecimal preMutualCapital;

    /**
     * 分摊人数
     */
    @ApiModelProperty(name="apportionmentNum",value = " 分摊人数")
    private BigDecimal apportionmentNum;

    /**
     * 全员每人预分摊
     */
    @ApiModelProperty(name="averageMoney",value = " 全员每人预分摊")
    private BigDecimal averageMoney;

    /**
     * 本期时间（yyyy-MM）
     */
    @ApiModelProperty(name="timeDate",value = " 本期时间（yyyy-MM）")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date timeDate;

    /**
     * 期数
     */
    @ApiModelProperty(name="timeDate",value = "期数")
    private Integer periodsNumber;



}

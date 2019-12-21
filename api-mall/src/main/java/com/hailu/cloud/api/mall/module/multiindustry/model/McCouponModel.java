package com.hailu.cloud.api.mall.module.multiindustry.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 11:
 */
@Data
@ApiModel
public class McCouponModel {


    /**
     * 商家编号
     */
    @ApiModelProperty(value = "商家编号")
    private Long numberId;

    /**
     * 商品总分类编号
     */
    @ApiModelProperty(value = "商品总分类编号")
    private Long storeTotalType;

    /**
     * 门店编号（1-为全部）
     */
    @ApiModelProperty(value = "门店编号（1-为全部）")
    private Long shopNumberId;

    /**
     * 卷名称
     */
    @ApiModelProperty(value = "卷名称")
    private String volumeName;

    /**
     * 到店卷面值(元)
     */
    @ApiModelProperty(value = "到店卷面值(元)")
    private BigDecimal bookValue;

    /**
     * 到店卷售价(元)
     */
    @ApiModelProperty(value = "到店卷售价(元)")
    private BigDecimal salesPrice;

    /**
     * 到店卷有效期类型（1-相对时间、2-指定时间）
     */
    @ApiModelProperty(value = "到店卷有效期类型（1-相对时间、2-指定时间）")
    private Integer validPeriodType;

    /**
     * 下单后有效时间（天）
     */
    @ApiModelProperty(value = "下单后有效时间（天）")
    private Date timeAfterOrder;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 文字介绍
     */
    @ApiModelProperty(value = "文字介绍")
    private String textIntroduction;

}

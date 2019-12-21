package com.hailu.cloud.api.mall.module.multiindustry.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/20 0020 10:28
 */
@Data
@ApiModel
public class McCoupon<T> implements Serializable {
    /**
     *  编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 文字介绍
     */
    @ApiModelProperty(value = "文字介绍")
    private String textIntroduction;

    /**
     * 审核(审核中-1,审核通过-2,审核不通过-3、待确认-4)
     */
    @ApiModelProperty(value = "审核(审核中-1,审核通过-2,审核不通过-3、待确认-4)")
    private Integer toExamine;

    /**
     * 上架状态(未上架-1、已上架-2、已下架-3)
     */
    @ApiModelProperty(value = "上架状态(未上架-1、已上架-2、已下架-3)")
    private Integer shelfState;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date dateTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateDateTime;

    /**
     * 其他字段(json格式)
     */
    @ApiModelProperty(value = "其他字段(json格式)")
    private String otherJson;

    /**
     * 其他字段(类格式)
     */
    @ApiModelProperty(value = "其他字段(类格式)")
    private T mcCouponOtherJsonModel;

    /**
     * mc_coupon
     */
    private static final long serialVersionUID = 1L;
}
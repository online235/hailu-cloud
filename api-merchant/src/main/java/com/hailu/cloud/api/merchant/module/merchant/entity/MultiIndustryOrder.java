package com.hailu.cloud.api.merchant.module.merchant.entity;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@InjectDict
@ApiModel
public class MultiIndustryOrder implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNumber;

    /**
     * 总类型
     */
    @ApiModelProperty(value = "总类型")
    private Long totalType;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String memberId;

    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号")
    private Long storeId;

    /**
     * 产品标题
     */
    @ApiModelProperty(value = "产品标题")
    private String productTitle;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String briefIntroduction;

    /**
     * 购买数量（默认1，扩展预留）
     */
    @ApiModelProperty(value = "购买数量（默认1，扩展预留）")
    private Long purchaseQuantity;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    /**
     * 会员名称
     */
    @ApiModelProperty(value = "会员名称")
    private String memberName;

    /**
     * 手机好码
     */
    @ApiModelProperty(value = "手机好码")
    private String phone;

    /**
     * 兑换码
     */
    @ApiModelProperty(value = "兑换码")
    private String exchangeCode;

    /**
     * 订单状态(未完成-1、已完成-2)
     */
    @ApiModelProperty(value = "订单状态(未完成-1、已完成-2)")
    private Integer state;


    @DictName(code = "ORDER_STATUS", joinField = "state")
    private String stateDisplay;


    /**
     * 下单时间
     */
    @ApiModelProperty(value = "下单时间")
    private Date orderTime;

    /**
     * mc_order
     */
    private static final long serialVersionUID = 1L;

}
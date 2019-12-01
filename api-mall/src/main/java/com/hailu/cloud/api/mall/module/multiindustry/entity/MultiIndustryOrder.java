package com.hailu.cloud.api.mall.module.multiindustry.entity;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@InjectDict
public class MultiIndustryOrder implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号", dataType = "Long")
    private Long id;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", dataType = "String")
    private String orderNumber;

    /**
     * 总类型
     */
    @ApiModelProperty(value = "总类型", dataType = "Long")
    private Long totalType;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", dataType = "String")
    private String memberId;

    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号", dataType = "Long")
    private Long storeId;

    /**
     * 产品标题
     */
    @ApiModelProperty(value = "产品标题", dataType = "String")
    private String productTitle;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", dataType = "String")
    private String briefIntroduction;

    /**
     * 购买数量（默认1，扩展预留）
     */
    @ApiModelProperty(value = "购买数量（默认1，扩展预留）", dataType = "Long")
    private Long purchaseQuantity;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", dataType = "BigDecimal")
    private BigDecimal price;

    /**
     * 会员名称
     */
    @ApiModelProperty(value = "会员名称", dataType = "String")
    private String memberName;

    /**
     * 手机好码
     */
    @ApiModelProperty(value = "店铺默认头像", dataType = "String")
    private String phone;

    /**
     * 兑换码
     */
    @ApiModelProperty(value = "兑换码", dataType = "String")
    private String exchangeCode;

    /**
     * 订单状态(未完成-1、已完成-2)
     */
    @ApiModelProperty(value = "订单状态(未完成-1、已完成-2)", dataType = "Integer")
    private Integer state;

    @ApiModelProperty(value = "订单状态", dataType = "String")
    @DictName(code = "ORDER_STATUS", joinField = "state")
    private String stateDisPlay;

    /**
     * 下单时间
     */
    @ApiModelProperty(value = "下单时间", dataType = "Date")
    private Date orderTime;

    /**
     * mc_order
     */
    private static final long serialVersionUID = 1L;

}
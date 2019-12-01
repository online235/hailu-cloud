package com.hailu.cloud.api.mall.module.multiindustry.entity;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
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
    private Long id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 总类型
     */
    private Long totalType;

    /**
     * 用户ID
     */
    private String memberId;

    /**
     * 商品编号
     */
    private Long storeId;

    /**
     * 产品标题
     */
    private String productTitle;

    /**
     * 简介
     */
    private String briefIntroduction;

    /**
     * 购买数量（默认1，扩展预留）
     */
    private Long purchaseQuantity;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 手机好码
     */
    private String phone;

    /**
     * 兑换码
     */
    private String exchangeCode;

    /**
     * 订单状态(未完成-1、已完成-2)
     */
    private Integer state;


    @DictName(code = "ORDER_STATUS", joinField = "state")
    private String stateDisPlay;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * mc_order
     */
    private static final long serialVersionUID = 1L;

}
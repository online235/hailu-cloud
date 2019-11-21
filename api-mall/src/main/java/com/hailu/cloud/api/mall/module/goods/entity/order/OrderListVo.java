package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;

import java.util.List;

/**
 * 订单返回页面
 *
 * @author xuzhijie
 */
@Data
public class OrderListVo {
    /**
     * 订单id
     */
    private int orderId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 订单状态
     */
    private int orderStatus;
    /**
     * 是否预定
     */
    private int isReserve;
    /**
     * 总金额
     */
    private double goodsAmount;
    /**
     * 是否评价 0_否 , 1_是
     */
    private int evaluateState;
    /**
     * 第一阶段是否支付
     */
    private int oneIsPay;
    /**
     * 第一阶段支付金额
     */
    private double reserveOneAmount;
    /**
     * 第二阶段支付金额
     */
    private double reserveTwoAmount;
    /**
     * 订单总金额
     */
    private double orderAmount;
    /**
     * 现在的时间
     */
    private long newTime;
    /**
     * 第二阶段开始支付时间
     */
    private long twoPayStartTime;
    /**
     * 第二阶段结束支付时间
     */
    private long twoPayEndTime;
    /**
     * 預定ID
     */
    private Integer reserveId;
    /**
     * 订单商品list
     */
    private List<OrderGoods> orderGoods;

}

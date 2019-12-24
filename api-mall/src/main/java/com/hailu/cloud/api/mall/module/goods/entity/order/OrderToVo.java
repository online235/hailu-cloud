package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 添加订单Vo
 *
 * @author Administrator
 */
@Data
public class OrderToVo {
    private int orderId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 订单生成时间
     */
    private Long createTime;
    /**
     * 订单支付时间
     */
    private Long paymentTime;
    /**
     * 订单完成时间
     */
    private Long accomplishTime;
    /**
     * 收货地址id
     */
    private int addressId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 运费
     */
    private Double freight;
    /**
     * 商品总金额
     */
    private BigDecimal goodsAmount;
    /**
     * 订单应付金额
     */
    private Double orderAmount;
    /**
     * 优惠金额
     */
    private Double discount;
    /**
     * 优惠卷金额
     */
    private Double couponPrice;
    /**
     * 订单状态：0:已取消,1:待付款,2:待发货,3:待收货,4:交易完成,5:已提交,6:已确认
     */
    private int orderState;
    /**
     * 优惠卷id
     */
    private Integer couponId;
    /**
     * 是否预定 1_是,0_否
     */
    private int isReserve;
    /**
     * 预定id
     */
    private Integer reserveId;
    /**
     * 第一阶段支付金额
     */
    private Double reserveOneAmount;
    /**
     * 第一階段是否支付
     */
    private int oneIsPay;
    /**
     * 第二階段是否支付
     */
    private int twoIsPay;
    /**
     * 第二阶段支付金额
     */
    private Double reserveTwoAmount;
    private String userName;
    /**
     * 支付狀態
     */
    private int paymentState;
    /**
     * 预定优惠金额
     */
    private Double reserveDiscounts;
    /**
     * 是否重新下单0_否 ,1_是
     */
    private int isRecour;
    /**
     * 0_ 1_是
     */
    private String isLimit;
    private Double amount;

    /**
     * 卖家店铺ID
     */
    private Integer storeId;

    /**
     * 邀请人ID
     */
    private String inviterUserId;


}

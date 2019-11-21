package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;

import java.util.Date;

/**
 * @author leiqi
 */
@Data
public class OrderDetailVo {
    private int id;
    /**
     * 订单id
     */
    private int orderId;
    /**
     * 商品单价
     */
    private double price;
    /**
     * 商品id
     */
    private int goodsId;
    /**
     * 数量
     */
    private int number;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 是否评价（0未评价1已评价）
     */
    private int isComment;
    /**
     * 是否申请了售后服务
     */
    private int isApplyFor;
    /**
     * 是否活动（0未活动1活动）
     */
    private int isActivity;
    /**
     * 活动类型
     */
    private int activityType;
    /**
     * 活动总价格
     */
    private double activityAmount;
    /**
     * 优惠时总价格
     */
    private double preferentialAmount;
    /**
     * 实际支付价格
     */
    private double realAmount;
    /**
     * 商品总价（不优惠，不打折扣）
     */
    private double amount;
    /**
     * 规格
     */
    private int goodsRuleId;
    /**
     * 审核状态  (状态待定)
     */
    private int reviewStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 规格详细
     */
    private String typeName;
    private Date gouTime;
    /**
     * 商品图片
     */
    private String smallImg;
    /**
     * 商品名称
     */
    private String goodsName;
    private ShoppingAddressVo shoppingAddressVo;


}

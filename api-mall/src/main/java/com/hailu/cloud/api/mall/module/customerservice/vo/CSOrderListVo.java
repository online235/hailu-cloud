package com.hailu.cloud.api.mall.module.customerservice.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class CSOrderListVo {
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
     * 订单id
     */
    private int orderStatus;
    /**
     * 总金额
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
     * 订单商品list
     */
    private List<CSOrderGoods> orderGoods;

}

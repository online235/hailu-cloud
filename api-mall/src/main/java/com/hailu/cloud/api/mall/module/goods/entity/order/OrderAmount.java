package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单中的金额
 *
 * @author Administrator
 */
@Data
@NoArgsConstructor
public class OrderAmount {
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品规格id
     */
    private Integer goodsSpecId;
    /**
     * 健康豆数量
     */
    private Integer integral;
    /**
     * 原价
     */
    private Double pledgePrice;
    /**
     * 商品金额
     */
    private BigDecimal goodsPrice;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 预约类型
     */
    private Integer reserveType;
    /**
     * 第一阶段金额
     */
    private Double onePayPrice;
    /**
     * 第二阶段金额
     */
    private Double twoPayPrice;


    public OrderAmount(Boolean flag) {
        this.pledgePrice = 0.0;
        this.goodsPrice = BigDecimal.ZERO;
        this.onePayPrice = 0.0;
        this.twoPayPrice = 0.0;
    }

    /**
     * 构造方法
     *
     * @param goodsId     商品id
     * @param goodsSpecId 商品规格id
     * @param goodsNum    商品数量
     */
    public OrderAmount(Integer goodsId, Integer goodsSpecId, Integer goodsNum) {
        this.goodsId = goodsId;
        this.goodsSpecId = goodsSpecId;
        this.goodsNum = goodsNum;
    }

}

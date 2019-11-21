package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

/**
 * 商品价格
 *
 * @author xuzhijie
 */
@Data
public class GoodsPriceVo {
    private int id;
    /**
     * 商品id
     */
    private int goodsId;
    /**
     * 商品规格id
     */
    private int goodsRuleId;
    /**
     * 原价
     */
    private double price;
    /**
     * 折扣价
     */
    private double discountPrice;
    /**
     * 活动价格
     */
    private double activityPrice;
    /**
     * 默认价格
     */
    private int isDefault;
    /**
     * 备注
     */
    private String remark;

    private int repertory;

    private String labelling;

    private int level;

    private int pid;
}

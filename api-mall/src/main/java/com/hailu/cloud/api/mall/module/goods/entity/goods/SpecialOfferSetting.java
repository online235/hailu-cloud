package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

import java.util.List;

/**
 * 会员特惠商品设置
 */
@Data
public class SpecialOfferSetting {
    private int sosId;
    /**
     * 类型 1_行一列,2_一行两列
     */
    private String sosType;
    /**
     * 序号
     */
    private int sort;
    /**
     * 会员特惠商品
     */
    private List<SpecialOfferGoods> offerGoodsList;

}

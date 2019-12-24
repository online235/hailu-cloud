package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

/**
 * 会员特惠商品
 *
 * @author Administrator
 */
@Data
public class SpecialOfferGoods {

    private Integer sogId;

    private Integer soId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 显示图片
     */
    private String showImg;

}

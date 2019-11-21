package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsAndGoodsSpecVo{

    /**
     * 商品编号
     */
    private Integer goodsId;

    /**
     * 商品默认图像
     */
    private String goodsImage;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 进货价格
     */
    private double specGoodsPurchasePrice;

    /**
     * 提成
     */
    private BigDecimal commission;
}

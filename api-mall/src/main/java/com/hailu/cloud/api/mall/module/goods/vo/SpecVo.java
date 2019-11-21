package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpecVo {
    private int specGoodsId; //商品规格id
    private String specName; //规格名
    private BigDecimal specGoodsPrice; //商品价格
    private BigDecimal specGoodsVipPrice; //商品会员价格
    private BigDecimal specGoodsPurchasePrice; //进货价格
    private BigDecimal commission; //提成
    private Integer specGoodsStorage; //商品库存
    private Integer specSalenum; //售出数量
    private String specGoodsColor; //商品颜色
    private String specGoodsSpec; //商品规格序列化
    private Integer goodsId;//商品id
    private String specGoodsSerial;//规格商品编号
    private String specIsopen;//是否开启规格,1:开启，0:关闭
    private Double weight;//重量
    private Double volume;//体积
}

package com.hailu.cloud.api.mall.module.goods.vo;


import lombok.Data;

/**
 * 运费模版
 *
 * @author Administrator
 */
@Data
public class FreightVo {
    private int id; //运费模板id
    private double sprice; //首件运费
    private int snum; //首件数量
    private int xnum; //续建数量
    private double xprice; //续建运费
    private String shippingType;//类型 1——重量 2——体积
    private int goodsId;//商品id
}

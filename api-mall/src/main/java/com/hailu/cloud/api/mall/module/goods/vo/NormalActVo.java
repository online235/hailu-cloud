package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

@Data
public class NormalActVo {
    private Integer goodsSpecId;//商品规格id
    private Double activityPrice;//活动价格
    private Integer activityRepertory; //活动库存
    private Double discountPrice; //折扣多少
    private Double specPrice; //默认价格
    /**
     * 会员价格
     */
    private double specGoodsVipPrice;
    /**
     * 进货价格
     */
    private double specGoodsPurchasePrice;
    private String remark; //备注
    private int hasBeen;//已购数量
}

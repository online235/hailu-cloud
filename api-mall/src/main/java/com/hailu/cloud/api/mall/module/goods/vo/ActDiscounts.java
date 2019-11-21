package com.hailu.cloud.api.mall.module.goods.vo;


import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ActDiscounts {
    private Integer gcId; // 二级类型id
    private Integer gcBigId; // 一级类型id
    private Integer goodsId; // 商品id
    private Integer actDiscountsId; // 活动优惠id
    private String discountsTitle; //促销标题
    private String discountsDescribe; //促销描述
    private long startTime; //促销开始时间
    private long endTime; //促销结束时间
    private Integer discountsStatus; //状态
}

package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

import java.util.Date;

/**
 * 首页商品信息
 *
 * @author xuzhijie
 */
@Data
public class GoodsTo {
    private int id;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品原价格
     */
    private double goodsPrice;
    /**
     * 商品描述
     */
    private String goodsDescribe;
    /**
     * 活动价格
     */
    private double activityPrice;
    /**
     * 商品图片（小图片）
     */
    private String smallImg;
    /**
     * 是否活动
     */
    private int isActivity;
    /**
     * 活动库存
     */
    private int activityRepertory;
    /**
     * 活动开始时间
     */
    private Date activityStartTime;
    /**
     * 活动结束时间
     */
    private Date activityEndTime;
    /**
     * 活动类型  1限时抢购  2新品首发  3.新品预定  4.特别推荐
     */
    private int activityType;
    /**
     * 当前时间
     */
    private Date currentTime;
    /**
     * 已抢
     */
    private int hasBeen;
    /**
     * 条件
     */
    private int conditions;
    /**
     * 排序
     */
    private String oby;
    /**
     * 分类id
     */
    private String categoryId;

}

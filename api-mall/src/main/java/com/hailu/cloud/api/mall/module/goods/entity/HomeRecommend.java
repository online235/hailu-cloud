package com.hailu.cloud.api.mall.module.goods.entity;

import lombok.Data;

import java.util.List;

/**
 * 首页商品推荐
 *
 * @author Administrator
 */
@Data
public class HomeRecommend {
    private Integer hrId;
    private Long createTime;
    /**
     * 类型 1_一行2列,2_一行一列
     */
    private String hrType;

    private Integer hrSort;

    private List<HomeRecommendGoods> homeRecommendGoods;
}

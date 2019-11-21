package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

import java.util.List;

/**
 * 每周精选
 *
 * @author Administrator
 */
@Data
public class WeekFeatured {

    private Integer wfsId;
    private Integer wfId;
    /**
     * 类型 1_广告图,2_商品
     */
    private String wfsType;
    /**
     * 1_一行2列,2_一行一列
     */
    private String wfsState;

    private Integer wfsSort;
    /**
     * list
     */
    private List<WeekFeaturedGoods> list;

}

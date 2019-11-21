package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class SearchGoodsParam {
    /**
     * 名称
     */
    private String name;
    /**
     * 分类id
     */
    private Integer classId;
    /**
     * 品牌id
     */
    private Integer brandId;
    /**
     * 规格id
     */
    private Integer specId;
    /**
     * 筛选 1: 销量(默认) 2：最新 3：价格
     */
    private Integer conditions;
    /**
     * 排序方式 asc/desc
     */
    private String order;
    /**
     * 页数
     */
    private int page;
    /**
     * 条数
     */
    private int rows = 10;

    public void setPage(int page) {
        page = page > 1 ? (page - 1) * this.rows : 0;
        this.page = page;
    }

}

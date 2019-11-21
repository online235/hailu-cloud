package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class GoodsRuleTo {
    private int id;
    /**
     * 规格分组名称
     */
    private String typeGroupName;
    /**
     * 规格
     */
    private String typeName;
    /**
     * 价格
     */
    private String price;
    /**
     * 活动价格
     */
    private String activityPrice;
    /**
     * 是否是默认价格  0 默认  1其他
     */
    private String isDefault;

}

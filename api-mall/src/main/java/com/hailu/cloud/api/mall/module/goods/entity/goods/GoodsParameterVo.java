package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

/**
 * 商品参数
 *
 * @author xuzhijie
 */
@Data
public class GoodsParameterVo {
    private int id;
    private int goodsId;
    /**
     * 参数名称
     */
    private String parameterName;
    /**
     * 参数值
     */
    private String parameterValue;

}

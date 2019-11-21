package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class GoodsRule {
    private int id;
    private int goodsId;
    private String ruleName;
    private int level;
    private List<GoodsPriceVo> goodsPriceVo;
}

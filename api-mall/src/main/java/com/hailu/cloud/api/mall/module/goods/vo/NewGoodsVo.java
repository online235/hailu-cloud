package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

@Data
public class NewGoodsVo {
    private String showImg; //新品首发商品图片
    private Integer goodsId; //商品id
    private String specName; //规格名
    private Integer specId; //规格id
    private String goodsName; //商品名
    private String goodsSubtitle; //商品副标题
    private String goodsDescription; //商品描述
    private Integer storeId; //店铺id
    private String storeName; //店铺名
    private Double goodsPrice; //商品价格
    private Integer hasBeen; //售出数量

}

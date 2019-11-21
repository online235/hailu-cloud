package com.hailu.cloud.api.mall.module.goods.vo;


import lombok.Data;

/**
 * 用户收藏
 *
 * @author leiqi
 */
@Data
public class CollectGoodsVo {
    private int id;
    private int goodsId;//商品id
    private String userId;//会员id
    private double collectPrice;//收藏价格
    private int goodsRuleId;//商品规格
    private String typeName;//规格详细
    private String name;//商品名称
    private String smallImg;//小图片
    private Integer gcId; //商品二级类型id
    private Integer gcBigId; //商品一级类型id
    private double price; //规格对应金额
    private long createTime;

}

package com.hailu.cloud.api.mall.module.goods.entity.goods;


import com.hailu.cloud.api.mall.util.Const;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author Administrator
 */
@Data
public class GoodsListVo {
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品副标题
     */
    private String goodsSubtitle;
    /**
     * 商品默认封面图片
     */
    private String goodsImage;
    /**
     * 商品描述
     */
    private String goodsDescription;
    /**
     * 店铺id
     */
    private Integer storeId;
    /**
     * 店铺名
     */
    private String storeName;
    /**
     * 规格id
     */
    private Integer specId;
    /**
     * 默认价格
     */
    private Double specGoodsPrice;
    /**
     * 会员价格
     */
    private Double specGoodsVipPrice;
    /**
     * 进货价
     */
    private Double specGoodsPurchasePrice;
    /**
     * 提成
     */
    private BigDecimal commission;
    /**
     * 商品二级类型id
     */
    private Integer gcId;
    /**
     * 商品一级类型id
     */
    private Integer gcBigId;
    /**
     * 是否活动 0_不参加,1_参加
     */
    private int isActivity;
    /**
     * 活动价格
     */
    private Double activityPrice;
    /**
     * 活动库存
     */
    private Integer activityRepertory;
    /**
     * 活动类型 1_限时抢购,2_正常活动,3_预定
     */
    private Integer activltyType;
    /**
     * 已抢购数量
     */
    private Integer hasBeen;
    /**
     * 活动开始时间
     */
    private long actStartTime;
    /**
     * 活动结束时间
     */
    private long actEndTime;

    /**
     * 是否参与推广 0_否  1_参与
     */
    private int isPopularize;

    public void setGoodsImage(String goodsImage) {
        if (StringUtils.isNotEmpty(goodsImage)) {
            goodsImage = Const.PRO_URL + goodsImage;
        }
        this.goodsImage = goodsImage;
    }

}

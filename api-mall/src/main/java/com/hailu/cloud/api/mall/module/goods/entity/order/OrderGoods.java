package com.hailu.cloud.api.mall.module.goods.entity.order;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class OrderGoods {
    /**
     * 订单商品id
     */
    private int recId;
    /**
     * 订单id
     */
    private int orderId;
    /**
     * 商品id
     */
    private int goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 规格id
     */
    private int specId;
    /**
     * 规格描述
     */
    private String specName;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品数量
     */
    private int goodsNum;
    /**
     * 商品图片
     */
    private String goodsImage;
    /**
     * 评价状态 0为评价，1已评价
     */
    private int evaluationStatus;
    /**
     * 评价时间
     */
    private long evaluationTime;
    /**
     * 商品实际成交价
     */
    private double goodsPayPrice;
    /**
     * 买家ID
     */
    private String buyerId;
    /**
     * 商品最底级分类ID
     */
    private int gcId;
    /**
     * 一级分类ID
     */
    private int gcBigId;
    /**
     * 類型商品类型 0常规商品,2耗配,3,赠品
     */
    private int type;
    /**
     * 父ID
     */
    private int parentId;
    private long createTime;
    /**
     * 赠品list
     */
    private List<GoodsCompl> goodsClmpl;
    /**
     * 商品重量(kg)
     */
    private BigDecimal weight;
    /**
     * 商品体积
     */
    private BigDecimal volume;
    /**
     * 体积单位
     */
    private double fullFreeMail;
    /**
     * 活动商品id
     */
    private Integer actPriceId;
}

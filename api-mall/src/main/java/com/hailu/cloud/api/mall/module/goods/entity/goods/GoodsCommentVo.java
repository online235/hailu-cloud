package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

/**
 * 评价
 *
 * @author xuzhijie
 */
@Data
public class GoodsCommentVo {
    /**
     * 评价id
     */
    private int gevalId;
    /**
     * 评价人id
     */
    private String userId;
    /**
     * 订单商品表编号
     */
    private int gevalOrderGoodsId;
    /**
     * 商品id
     */
    private int goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 评价内容
     */
    private String gevalContent;
    /**
     * 商品价格
     */
    private Double goodsPrice;
    /**
     * 描述评分 5分 一分一颗星
     */
    private int gevalScore;
    /**
     * 好评1   中评 2   差评3
     */
    private int comment;
    /**
     * 物流评分  5分 一分一颗星
     */
    private int logisticsScore;
    /**
     * 服务评分  5分 一分一颗星
     */
    private int serviceScore;
    /**
     * 是否匿名评论   0匿名   1不匿名
     */
    private int gevalIsAnonymous;
    /**
     * 晒单图片 多张图片,分隔
     */
    private String gevalImage;
    /**
     * 订单id
     */
    private int orderId;
    /**
     * 评价时间
     */
    private long createTime;
    /**
     * 评价人头像
     */
    private String userImg;
    /**
     * 评价人昵称
     */
    private String userName;
    /**
     * 规格名
     */
    private String specInfo;
    /**
     * 是否删除 0:未删除;1.已删除 默认0
     */
    private int isDel;
    private String goodsComments;
}

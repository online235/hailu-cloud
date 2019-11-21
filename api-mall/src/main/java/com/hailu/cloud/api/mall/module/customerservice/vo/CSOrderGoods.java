package com.hailu.cloud.api.mall.module.customerservice.vo;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class CSOrderGoods {
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
    private double goodsPrice;
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
     * 類型商品类型 0常规商品,1免费,2耗配,3,赠品
     */
    private int type;
    /**
     * 父id
     */
    private int parentId;
    /**
     * 该商品赠送积分
     */
    private int goodsIntegral;
    /**
     * 赠品list
     */
    private List<GoodsCompl> goodsClmpl;
    private int csApplyId;
    /**
     * 0就是 没正在进行中的售后流程  1显示申请中 2显示为空
     */
    private int status;
}

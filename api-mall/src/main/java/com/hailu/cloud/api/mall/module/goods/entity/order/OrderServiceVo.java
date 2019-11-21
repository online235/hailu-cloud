package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Data
public class OrderServiceVo {
    private int id;
    private String userId;
    private String applyForTime;
    /**
     * 订单id
     */
    private int orderId;
    /**
     * 商品单价
     */
    private double price;
    /**
     * 商品id
     */
    private int goodsId;
    /**
     * 数量
     */
    private int number;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 商品图片
     */
    private String smallImg;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 图片
     */
    private String picture;
    /**
     * 售后原因 即内容
     */
    private String reason;
    /**
     * 售后进度
     */
    private String state;
    /**
     * 服务类型
     */
    private String serviceType;
    /**
     * 服务单号
     */
    private String serviceNumber;
    /**
     * 订单明细id
     */
    private String orderDetailId;
    /**
     * 收货地址id
     */
    private String addressId;
    /**
     * 处理结果描述
     */
    private String resultremark;
    /**
     * 时间
     */
    private String time;
    private List<Map<String, String>> progressTime;

}

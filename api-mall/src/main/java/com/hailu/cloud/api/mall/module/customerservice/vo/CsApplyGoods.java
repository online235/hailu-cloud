package com.hailu.cloud.api.mall.module.customerservice.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class CsApplyGoods {
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 订单id
     */
    private int orderId;
    /**
     * 商品数量
     */
    private int goodsNum;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsImage;
    /**
     * 服务类型
     */
    private List<Integer> csTypes;
    /**
     * 订单id
     */
    private int orderGoodsId;
    /**
     * 订单id
     */
    private int goodsId;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 收件人手机号码
     */
    private String addresseePhn;
    /**
     * 收货地址
     */
    private String receivingAddress;
    /**
     * 运费
     */
    private Double freight;
    /**
     * 商品总金额
     */
    private Double orderAmount;

}

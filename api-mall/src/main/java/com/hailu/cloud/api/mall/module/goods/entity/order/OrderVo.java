package com.hailu.cloud.api.mall.module.goods.entity.order;

import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 订单
 *
 * @author leiqi
 */
@Data
public class OrderVo {
    private Integer orderId;
    private Long createTime;
    private int id;
    private String orderNumber;
    private double price;
    private Date gouTime;
    private String userId;
    private Date payTime;
    /**
     * 支付方式1微信支付2支付宝支付3银联银行卡
     */
    private int payMethod;
    private int shoppingAddressId;
    private String remark;
    private int number;
    private int isSend;
    private int payStatus;
    private String orderStatus;
    /**
     * 运费
     */
    private double freight;
    /**
     * 运单号
     */
    private String theAwb;
    /**
     * 快递公司编码
     */
    private String shipperCode;
    /**
     * 支付交易单号
     */
    private String payNumber;
    private Integer isYd;
    private List<OrderDetailVo> orderDetails;
    private ShoppingAddressVo shoppingAddressVo;

}

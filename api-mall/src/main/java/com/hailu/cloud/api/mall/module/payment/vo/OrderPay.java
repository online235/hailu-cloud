package com.hailu.cloud.api.mall.module.payment.vo;

import lombok.Data;

/**
 * 用於添加支付詳情的
 *
 * @author 黄亮  E-mail 1428516543@qq.com
 */
@Data
public class OrderPay {
    private int payId; //支付id
    private String paySn;  //訂單編號
    private String buyerId;  //賣家id
    private int apiPayState; //支付狀態
    private int payType; //1支付宝 ,2微信 ,3微信\(^o^)/~H5
    private double payAmount; //支付金额
    private long payTime; // 支付时间 ;
    private String orderSn;//訂單編號
    private String wechatSource; //微信来源
}

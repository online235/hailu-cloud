package com.hailu.cloud.api.mall.module.sys.vo;

import lombok.Data;

@Data
public class OrderToPay {
    private Integer orderId;
    private String paymentName;
    private Integer paymentState;
    private Integer orderState;
    private long paymentTime;
    private Integer oneIsPay;
    private Integer twoIsPay;

}

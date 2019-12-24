package com.hailu.cloud.common.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付回调实体
 * @author junpei.deng
 */
@Data
@AllArgsConstructor
public class PayCallbackModel {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 第三方交易号
     */
    private String tradeNo;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 支付类型 1-支付宝、2-微信
     */
    private Integer payType;
}

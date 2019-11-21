package com.hailu.cloud.api.mall.module.goods.enums;

import lombok.Getter;

/**
 * 这个是交易流水号的
 *
 * @author Administrator
 */
public enum SerialEnum {
    /**
     * 订单号
     */
    ZT(1, "zt"),
    /**
     * 店铺订单
     */
    DP(2, "DP"),
    /**
     * 售后
     */
    SH(3, "SH"),
    /**
     * 提现
     */
    TX(4, "TX"),
    /**
     * 余额
     */
    YE(5, "YE"),
    /**
     * 健康豆商城兑换
     */
    DH(6, "DH");

    @Getter
    private int code;

    @Getter
    private String value;

    SerialEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}
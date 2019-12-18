package com.hailu.cloud.common.enums;

import lombok.Getter;

/**
 * @author xuzhijie
 */
public enum OrderNumberEnum {

    // region 心安

    /**
     * 心安-加入会员
     */
    XA_JOIN_MEMBER("XA", "00"),

    // endregion

    // region 商城

    /**
     * 商城-商品订单
     */
    SC_GOODS_ORDER("SC", "00"),

    /**
     * 商城-加入会员
     */
    SC_JOIN_MEMBER("SC", "01"),

    // endregion

    // region 美食

    /**
     * 美食-预约订单
     */
    MS_JOIN_MEMBER("MS", "00"),

    // endregion

    // region 酒店

    /**
     * 酒店-预约订单
     */
    JD_JOIN_MEMBER("JD", "00"),

    // endregion
    ;

    /**
     * 系统编号
     */
    @Getter
    private String system;

    /**
     * 业务编号 加入会员
     */
    @Getter
    private String businessCode;

    OrderNumberEnum(String system, String businessCode){
        this.system = system;
        this.businessCode = businessCode;
    }

}

package com.hailu.cloud.api.merchant.module.eunms;

import lombok.Getter;

/**
 * 审核类型、商户类型
 *
 * @author: QiuFeng:WANG
 */
public enum Mceunm {

    IN_AUDIT(1, "审核中"),
    AUDIT_PASS(2, "审核通过"),
    AUDIT_FAILED(3, "审核不通过"),

    DELICIOUS_FOOD(1, "美食"),
    DEPARTMENT_STORE_SHOPPING(2, "百货购物"),
    HOTEL_ACCOMMODATION(3, "酒店住宿"),
    SCENIC_SPOT(4, "旅游景点"),
    RECREATION_ENTERTAINMENT(5, "休闲娱乐"),
    LIFE_SERVICE(5, "生活服务"),
    VENDOR_ALLIANCE(6, "厂商联盟");


    /**
     * key键
     */
    @Getter
    private int key;

    /**
     * value值
     */
    @Getter
    private String value;

    Mceunm(int key, String value) {
        this.key = key;
        this.value = value;
    }

}

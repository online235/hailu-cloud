package com.hailu.cloud.api.mall.module.multiindustry.enums;

import lombok.Getter;

public enum  OrderEnums {

    IN_BUSINESS(1,"营业中"),
    REST(2,"休息中");

    @Getter
    private int key;

    @Getter
    private String value;

    OrderEnums(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

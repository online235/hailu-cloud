package com.hailu.cloud.api.merchant.module.merchant.eunms;

import lombok.Getter;

public enum MultiIndustryOrderEnums {

    IN_BUSINESS(1,"营业中"),
    REST(2,"休息中");

    @Getter
    private int key;

    @Getter
    private String value;

    MultiIndustryOrderEnums(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

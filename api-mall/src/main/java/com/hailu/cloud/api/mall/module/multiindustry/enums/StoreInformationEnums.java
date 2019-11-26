package com.hailu.cloud.api.mall.module.multiindustry.enums;

import lombok.Getter;

public enum  StoreInformationEnums {

    IN_AUDIT(1,"审核中"),
    AUDIT_PASS(2,"审核通过"),
    AUDIT_FAILED(3,"审核不通过");

    @Getter
    private int key;

    @Getter
    private String value;

    StoreInformationEnums(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

package com.hailu.cloud.api.xinan.module.enums;

import lombok.Getter;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安救助类型枚举
 * @Date: 16:33 2019/11/2 0002
 */
public enum MutualEnum {


    STUDENT_ASSISTANCE(1,"助学"),
    AID_THE_DISABLED(2,"助残"),
    HELP_AGED(3,"助老"),
    DISEASE(4,"疾病"),
    POVERTY_ALLEVIATION(5,"扶贫"),
    PUBLIC_WELFARE(6,"公益"),
    DISASTER_RELIEF(7,"救灾"),
    MEDICAL_CARE(8,"医疗"),
    OBTAIN_EMPLOYMENT(9,"就业"),
    NATURAL(10,"自然");

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

    MutualEnum(int key, String value){
        this.key = key;
        this.value = value;
    }
}

package com.hailu.cloud.api.mall.module.user.enums;

import lombok.Getter;

/**
 * @Description TODO
 * @Author 刘柱栋
 * @Date 2016/6/21 23:29
 * @Copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @Version v1.0
 */
public enum PrivilegeModuleType {
    ARTICLE(1, "文章"),
    INVEST_GROUP(2, "组合"),
    ;

    @Getter
    private int index;

    @Getter
    private String desc;

    PrivilegeModuleType(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

}



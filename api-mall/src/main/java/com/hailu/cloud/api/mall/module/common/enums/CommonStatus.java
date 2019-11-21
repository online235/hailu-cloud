package com.hailu.cloud.api.mall.module.common.enums;

/**
 * @author 刘柱栋
 * @Description 通用状态
 * @date 2016/6/11 21:10
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
public enum CommonStatus implements BaseEnum {
    VALID(1, "生效的数据状态"),
    DISABLE(2, "禁用"),
    DELETE(3, "删除");

    private int index;
    private String desc;

    CommonStatus(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

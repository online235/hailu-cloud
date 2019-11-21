package com.hailu.cloud.api.mall.module.common.enums;

/**
 * Created by liuzhudong on 16/6/25.
 */
public enum UserTaskType implements BaseEnum {
    ONE_TIME(0, "一次任务"),
    EVERY_DAY(1, "每天任务");

    private int index;
    private String desc;

    UserTaskType(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public String getDesc() {
        return null;
    }
}

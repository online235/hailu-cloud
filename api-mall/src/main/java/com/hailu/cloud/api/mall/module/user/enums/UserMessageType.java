package com.hailu.cloud.api.mall.module.user.enums;

import lombok.Getter;

/**
 * Created by liuzhudong on 16/6/25.
 */
public enum UserMessageType {
    USER_MSG(1, "用户消息"),
    SYS_MSG(2, "系统公告"),
    USER_QUESTION(3, "用户提问"),
    GROUP_MSG(4, "投资组合公告");

    @Getter
    private int index;

    @Getter
    private String desc;

    UserMessageType(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

}

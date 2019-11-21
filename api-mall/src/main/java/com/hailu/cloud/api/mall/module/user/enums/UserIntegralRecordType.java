package com.hailu.cloud.api.mall.module.user.enums;

import lombok.Getter;

/**
 * Created by liuzhudong on 16/6/25.
 */
public enum UserIntegralRecordType {
    BUY(2, "功能兑换"),
    FRIEND(3, "推荐好友"),
    FEEDBACK(4, "意见反馈"),
    SHARE(5, "转发资讯"),
    READ(6, "阅读资讯"),
    QUESTION(7, "在线提问")
    ;

    @Getter
    private int index;

    @Getter
    private String desc;

    UserIntegralRecordType(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

}

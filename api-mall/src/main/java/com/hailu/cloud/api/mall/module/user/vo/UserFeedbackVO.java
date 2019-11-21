package com.hailu.cloud.api.mall.module.user.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by somebody on 2016/6/17.
 * 提问反馈表
 */
@Data
public class UserFeedbackVO implements Serializable {
    /**
     * id
     */
    private int id;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 邮件地址
     */
    private String email;

    /**
     * 问题内容
     */
    private String content;

    /**
     * 创建时间
     */
    private long createTime;

}

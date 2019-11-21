package com.hailu.cloud.api.mall.module.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by somebody on 2016/6/17.
 * 提问反馈表
 */
@Data
public class UserFeedback implements Serializable {
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
    private Date createTime;

}

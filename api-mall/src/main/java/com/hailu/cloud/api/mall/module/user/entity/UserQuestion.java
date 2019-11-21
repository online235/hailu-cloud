package com.hailu.cloud.api.mall.module.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 用户提问
 * @Author 刘柱栋
 * @Date 2016/6/20 20:49
 * @Copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @Version v1.0
 */
@Data
public class UserQuestion implements Serializable {

    private static final long serialVersionUID = -3464047784906247030L;

    /**
     * ID
     */
    private int id;

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 提问标题
     */
    private String questionTitle;
    /**
     * 提问
     */
    private String question;

    /**
     * 解答
     */
    private String answer;
    /**
     * 解答头像
     */
    private String answerIcon;

    /**
     * 解答时间
     */
    private Date answerTime;

    /**
     * 提问时间
     */
    private Date createTime;
    /**
     * 阅读时间
     */
    private Date readTime;
    /**
     * 分析师id
     */
    private int bondmanId;

}

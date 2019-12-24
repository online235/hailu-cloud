package com.hailu.cloud.api.merchant.module.merchant.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户反馈
 *
 * @author zhijie
 */
@Data
public class ShopFeedback {

    /**
     * ID
     */
    private Long id;

    /**
     * 处理人id
     */
    private Long handleId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 上传凭证，（多张用“,”拼接）
     */
    private String voucher;

    /**
     * IOS 1,ANDROID 2,other 3
     */
    private Integer phoneType;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名字
     */
    private String userName;

    /**
     * 回复
     */
    private String officialReply;

    /**
     * 处理状态 1、未处理；2、已经处理
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}

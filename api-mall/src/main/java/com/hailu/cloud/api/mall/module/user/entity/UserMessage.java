package com.hailu.cloud.api.mall.module.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户系统公告
 * Created by somebody on 2016/6/15.
 */
@Data
public class UserMessage implements Serializable {

    private static final long serialVersionUID = -638637956277689959L;

    /**
     * 消息id
     */
    private int id;

    /**
     * 用户id
     */
    private String userId;

    private int msgType;

    private String msgTitle;

    private String msgContent;

    private Date createTime;

    private Date readTime;

    private int sysMsgId;

}

package com.hailu.cloud.api.mall.module.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by somebody on 2016/6/17.
 * 用户签到表
 */
@Data
public class UserSign implements Serializable {
    private static final long serialVersionUID = 7435018660518997848L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 连续签到的天数
     */
    private int serialSignDay;
    /**
     * 本次签到获取的积
     */
    private int integral;
    /**
     * 签到时间
     */
    private Date lastSignTime;

}

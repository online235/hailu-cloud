package com.hailu.cloud.api.mall.module.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by somebody on 2016/6/17.
 * 用户签到表
 */
@Data
public class UserSignVO implements Serializable {
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


    private boolean flag;

}

package com.hailu.cloud.api.notify.module.sms.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 短信的接口返回的格式就是这种非驼峰的，没办法
 *
 * @author zhijie
 */
@Getter
@Setter
public class SmsModel {

    /**
     * 消息
     */
    private String MsgState;
    /**
     * 状态 0 成功
     */
    private int State;
    /**
     * 消息ID
     */
    private String MsgID;
    /**
     *
     */
    private int Reserve;
}

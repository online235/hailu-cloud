package com.hailu.cloud.api.notify.module.sms.model;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(name = "MsgState")
    private String msgState;
    /**
     * 状态 0 成功
     */
    @JSONField(name = "State")
    private int state;
    /**
     * 消息ID
     */
    @JSONField(name = "MsgID")
    private String msgId;
    /**
     *
     */
    @JSONField(name = "Reserve")
    private int reserve;
}

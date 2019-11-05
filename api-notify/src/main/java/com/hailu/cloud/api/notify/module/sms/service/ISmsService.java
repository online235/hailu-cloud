package com.hailu.cloud.api.notify.module.sms.service;

import com.hailu.cloud.api.notify.module.sms.model.SmsModel;

/**
 * @author zhijie
 */
public interface ISmsService {

    /**
     * 发送短信
     *
     * @param phone   手机号码
     * @param message 短信内容
     * @return
     */
    SmsModel send(String phone, String message);

}

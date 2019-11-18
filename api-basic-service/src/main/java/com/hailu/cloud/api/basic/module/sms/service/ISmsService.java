package com.hailu.cloud.api.basic.module.sms.service;

import com.hailu.cloud.common.exception.BusinessException;

/**
 * @author zhijie
 */
public interface ISmsService {
    /**
     * 发送短信
     *
     * @param phone   手机号码
     * @param message 短信内容
     * @throws BusinessException
     */
    void send(String phone, String message) throws BusinessException;

}

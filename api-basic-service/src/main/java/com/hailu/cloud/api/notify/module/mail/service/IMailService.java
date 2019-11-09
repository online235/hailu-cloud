package com.hailu.cloud.api.notify.module.mail.service;

import com.hailu.cloud.api.notify.module.mail.model.MailModel;
import com.hailu.cloud.common.exception.BusinessException;

/**
 * 邮件发送服务
 *
 * @author zhijie
 */
public interface IMailService {

    /**
     * 发送一个简单格式的邮件
     *
     * @param mailModel
     * @throws BusinessException
     */
    void sendSimpleMail(MailModel mailModel) throws BusinessException;

    /**
     * 发送一个HTML格式的邮件
     *
     * @param mailModel
     * @throws BusinessException
     */
    void sendHtmlMail(MailModel mailModel) throws BusinessException;

}

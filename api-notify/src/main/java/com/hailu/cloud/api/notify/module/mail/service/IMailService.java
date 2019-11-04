package com.hailu.cloud.api.notify.module.mail.service;

import com.hailu.cloud.api.notify.module.mail.model.MailModel;

/**
 * 邮件发送服务
 * @author zhijie
 */
public interface IMailService {

    /**
     * 发送一个简单格式的邮件
     *
     * @param mailModel
     * @return
     */
    boolean sendSimpleMail(MailModel mailModel);

    /**
     * 发送一个HTML格式的邮件
     *
     * @param mailModel
     * @return
     */
    boolean sendHtmlMail(MailModel mailModel);

}

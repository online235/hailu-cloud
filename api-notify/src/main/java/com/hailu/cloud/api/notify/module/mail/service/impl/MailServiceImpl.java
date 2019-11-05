package com.hailu.cloud.api.notify.module.mail.service.impl;

import com.hailu.cloud.api.notify.module.mail.model.MailModel;
import com.hailu.cloud.api.notify.module.mail.service.IMailService;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @author zhijie
 */
@Slf4j
@Service
public class MailServiceImpl implements IMailService {

    @Value("${mail.sender}")
    private String defaultSender;

    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 发送一个简单格式的邮件
     *
     * @param mailModel
     */
    @Override
    public void sendSimpleMail(MailModel mailModel) throws BusinessException {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            String sender = StringUtils.isBlank(mailModel.getSender()) ? defaultSender : mailModel.getSender();
            simpleMailMessage.setFrom(sender);
            //邮件接收人
            simpleMailMessage.setTo(mailModel.getRecipient());
            //邮件主题
            simpleMailMessage.setSubject(mailModel.getSubject());
            //邮件内容
            simpleMailMessage.setText(mailModel.getContent());
            if (mailModel.getCc() != null) {
                simpleMailMessage.setCc(mailModel.getCc());
            }
            if (mailModel.getBcc() != null) {
                simpleMailMessage.setBcc(mailModel.getBcc());
            }
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            throw new BusinessException("邮件发送失败", e);
        }
    }

    /**
     * 发送一个HTML格式的邮件
     *
     * @param mailModel
     */
    @Override
    public void sendHtmlMail(MailModel mailModel) throws BusinessException {
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            //邮件发送人
            String sender = StringUtils.isBlank(mailModel.getSender()) ? defaultSender : mailModel.getSender();
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mailModel.getRecipient());
            mimeMessageHelper.setSubject(mailModel.getSubject());
            mimeMessageHelper.setText(mailModel.getContent(), true);
            if (mailModel.getCc() != null) {
                mimeMessageHelper.setCc(mailModel.getCc());
            }
            if (mailModel.getBcc() != null) {
                mimeMessageHelper.setBcc(mailModel.getBcc());
            }
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            throw new BusinessException("邮件发送失败", e);
        }
    }

}

package com.hailu.cloud.api.notify.module.mail.controller;

import com.hailu.cloud.api.notify.module.mail.model.MailModel;
import com.hailu.cloud.api.notify.module.mail.service.IMailService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "邮件")
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IMailService mailService;

    @ApiOperation(value = "发送纯文本邮件", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
            "}" +
            "</pre>")
    @PostMapping("/send/simple")
    public ApiResponse sendSimpleMail(@Valid MailModel mailModel) throws BusinessException {
        mailService.sendSimpleMail(mailModel);
        return ApiResponse.result();
    }

    @ApiOperation(value = "发送HTML格式邮件", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
            "}" +
            "</pre>")
    @PostMapping("/send/html")
    public ApiResponse sendHtmlMail(@Valid MailModel mailModel) throws BusinessException {
        mailService.sendHtmlMail(mailModel);
        return ApiResponse.result();
    }

}

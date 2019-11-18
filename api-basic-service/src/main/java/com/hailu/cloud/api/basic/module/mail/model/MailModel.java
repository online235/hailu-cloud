package com.hailu.cloud.api.basic.module.mail.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author zhijie
 */
@Getter
@Setter
@ApiModel
public class MailModel {
    /**
     * 邮件发送者
     */
    @ApiModelProperty("邮件发送者")
    private String sender;
    /**
     * 邮件接收人
     */
    @NotBlank(message = "邮件接收人不能为空")
    @ApiModelProperty("邮件接收人")
    private String recipient;
    /**
     * 抄送
     */
    @ApiModelProperty("抄送")
    private String[] cc;
    /**
     * 密送
     */
    @ApiModelProperty("密送")
    private String[] bcc;
    /**
     * 邮件主题
     */
    @NotBlank(message = "邮件主题不能为空")
    @ApiModelProperty("邮件主题")
    private String subject;
    /**
     * 邮件内容
     */
    @NotBlank(message = "邮件内容不能为空")
    @ApiModelProperty("邮件内容")
    private String content;

}
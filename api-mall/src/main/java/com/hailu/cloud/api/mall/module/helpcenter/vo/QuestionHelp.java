package com.hailu.cloud.api.mall.module.helpcenter.vo;


import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class QuestionHelp {
    /**
     * 问题帮助主键id
     */
    private Integer questionHelpId;
    /**
     * 帮助标签主键id
     */
    private Integer helpLinkId;
    /**
     * 问题
     */
    private String question;
    /**
     * 内容
     */
    private String content;
    /**
     * 标签名
     */
    private String helpLinkName;

}

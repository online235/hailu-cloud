package com.hailu.cloud.api.mall.module.helpcenter.service;


import com.hailu.cloud.api.mall.module.helpcenter.vo.HelpLink;
import com.hailu.cloud.api.mall.module.helpcenter.vo.QuestionHelp;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface IHelpLinkService {
    /**
     * 获取帮助中心问题标签
     **/
    List<HelpLink> getHelpLinkList();

    /**
     * 获取帮助中心问题列表
     **/
    List<QuestionHelp> getQuestionHelpList(int start, int rows, int helpLinkId);

    /**
     * 获取帮助中中心问题详情
     **/
    Map<String, Object> getQuestionHelpInfo(int start, int rows, int helpLinkId, int questionHelpId);
}

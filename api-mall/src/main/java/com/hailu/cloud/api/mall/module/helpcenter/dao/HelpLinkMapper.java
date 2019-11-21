package com.hailu.cloud.api.mall.module.helpcenter.dao;


import com.hailu.cloud.api.mall.module.helpcenter.vo.HelpLink;
import com.hailu.cloud.api.mall.module.helpcenter.vo.QuestionHelp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface HelpLinkMapper {
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
    QuestionHelp getQuestionHelpInfo(int questionHelpId);

    /**
     * 获取除本问题除外的问题
     **/
    List<QuestionHelp> getQuestionHelpInfoList1(int start, int rows, int helpLinkId, int questionHelpId);
}

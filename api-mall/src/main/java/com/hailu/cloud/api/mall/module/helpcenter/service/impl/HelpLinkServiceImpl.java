package com.hailu.cloud.api.mall.module.helpcenter.service.impl;

import com.hailu.cloud.api.mall.module.helpcenter.dao.HelpLinkMapper;
import com.hailu.cloud.api.mall.module.helpcenter.service.IHelpLinkService;
import com.hailu.cloud.api.mall.module.helpcenter.vo.HelpLink;
import com.hailu.cloud.api.mall.module.helpcenter.vo.QuestionHelp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class HelpLinkServiceImpl implements IHelpLinkService {

    @Resource
    private HelpLinkMapper helpLinkDao;

    /**
     * 获取帮助中心问题标签
     **/
    @Override
    public List<HelpLink> getHelpLinkList() {
        return helpLinkDao.getHelpLinkList();
    }

    /**
     * 获取帮助中心问题列表
     **/
    @Override
    public List<QuestionHelp> getQuestionHelpList(int start, int rows, int helpLinkId) {
        return helpLinkDao.getQuestionHelpList(start, rows, helpLinkId);
    }

    /**
     * 获取帮助中中心问题详情
     **/
    @Override
    public Map<String, Object> getQuestionHelpInfo(int start, int rows, int helpLinkId, int questionHelpId) {
        Map<String, Object> map = new HashMap<String, Object>();
        QuestionHelp questionHelp = helpLinkDao.getQuestionHelpInfo(questionHelpId);
        //获取除本问题除外的问题
        List<QuestionHelp> questionHelpList = helpLinkDao.getQuestionHelpInfoList1(start, rows, helpLinkId, questionHelpId);
        map.put("questionHelp", questionHelp);
        map.put("questionHelpList", questionHelpList);
        return map;
    }
}

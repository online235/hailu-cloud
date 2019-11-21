package com.hailu.cloud.api.mall.module.helpcenter.controller;

import com.hailu.cloud.api.mall.module.helpcenter.service.IHelpLinkService;
import com.hailu.cloud.api.mall.module.helpcenter.vo.HelpLink;
import com.hailu.cloud.api.mall.module.helpcenter.vo.QuestionHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/helpCentre")
public class HelpLinkController {
    @Autowired
    private IHelpLinkService helpLinkService;

    /**
     * 获取帮助中心问题标签列表
     **/
    @GetMapping("/helpLink/getHelpLinkList")
    public List<HelpLink> getHelpLinkList() {
        return helpLinkService.getHelpLinkList();
    }

    /**
     * 获取帮助中心问题列表
     **/
    @GetMapping("/questionHelp/getQuestionHelpList")
    public List<QuestionHelp> getQuestionHelpList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int rows, int helpLinkId) {
        int start = (page - 1) * rows;
        return helpLinkService.getQuestionHelpList(start, rows, helpLinkId);
    }

    /**
     * 获取帮助中中心问题详情
     **/
    @GetMapping("/questionHelp/getQuestionHelpInfo")
    public Map<String, Object> getQuestionHelpInfo(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int rows, @RequestParam int helpLinkId, @RequestParam int questionHelpId) {
        int start = (page - 1) * rows;
        return helpLinkService.getQuestionHelpInfo(start, rows, helpLinkId, questionHelpId);
    }

}

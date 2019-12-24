package com.hailu.cloud.api.merchant.module.merchant.controller;


import com.hailu.cloud.api.merchant.module.merchant.entity.ShopFeedback;
import com.hailu.cloud.api.merchant.module.merchant.parameter.ShopInformationEntryParameter;
import com.hailu.cloud.api.merchant.module.merchant.parameter.StoreFeedBackParameter;
import com.hailu.cloud.api.merchant.module.merchant.service.FeedBackService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangmugui
 */
@RestController
@RequestMapping("/app/feedBack")
@Validated
@Api(tags = "商户-物联云商-APP反馈管理")
@Slf4j
public class FeedBackController {

    @Resource
    private FeedBackService feedBackService;


    @ApiOperation(value = "提交反馈信息")
    @PostMapping("/submitFeedBack")
    public void submitFeedBack(@RequestBody StoreFeedBackParameter storeFeedBackParameter) throws BusinessException {

        ShopFeedback shopFeedback = new ShopFeedback();
        BeanUtils.copyProperties(storeFeedBackParameter,shopFeedback);
        feedBackService.saveFeedBack(shopFeedback);
    }




}

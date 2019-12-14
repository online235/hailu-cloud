package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.parameter.ShopInformationEntryParameter;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McInfoService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商家入驻APP
 * @Date: 19:10 2019/11/3 0003
 */
@RestController
@RequestMapping("/app/merchantLogin")
@Validated
@Api(tags = "商户-商家入驻-百货-APP")
@Slf4j
public class McUserAppContrller {

    @Autowired
    private McInfoService mcInfoService;


    @Autowired
    private RedisStandAloneClient redisStandAloneClient;

    @ApiOperation(value = "商家注册以及入驻", notes = "<pre>" +
            "{\n" +
            "  \"code\": 807,\n" +
            "  \"msg\": \"用户已存在\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987027320\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 233,\n" +
            "  \"msg\": \"手机号码已绑定\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987129871\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 207,\n" +
            "  \"msg\": \"无效的验证码\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987176455\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 204,\n" +
            "  \"msg\": \"数据状态不正确\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987027320\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 311,\n" +
            "  \"msg\": \"信息已存在\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987027320\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 201,\n" +
            "  \"msg\": \"参数错误\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987027320\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1572339258708\n" +
            "}")
    @PostMapping("register")
    public void register(
            @ModelAttribute ShopInformationEntryParameter shopInformationEntryParameter,
            BindingResult result) throws Exception {

        if (result.hasErrors()) {
            throw new BusinessException("必填信息不能为空！");
        }
        if(shopInformationEntryParameter.getIdCard().length() != 18){
            throw new BusinessException("身份证长度不符合");
        }
        String veriCode = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + shopInformationEntryParameter.getMoli() + 1);
        if (!shopInformationEntryParameter.getCode().equals(veriCode) ) {
            // 验证码不存在
            throw new BusinessException("无效验证码");
        }
        mcInfoService.addMcUserAndEntry(shopInformationEntryParameter, 2);

    }


}

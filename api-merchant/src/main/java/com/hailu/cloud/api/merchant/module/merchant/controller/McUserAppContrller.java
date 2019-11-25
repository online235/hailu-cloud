package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.lifecircle.entity.LocalCircleEntry;
import com.hailu.cloud.api.merchant.module.lifecircle.parameter.RegisterInformation;
import com.hailu.cloud.api.merchant.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.merchant.parameter.ShopInformationEntryParameter;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McInfoService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商家入驻APP
 * @Date: 19:10 2019/11/3 0003
 */
@RestController
@RequestMapping("/app/merchantLogin")
@Validated
@Api(tags = "商户-商家入驻-APP")
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
    @ResponseBody
    public void register(@ModelAttribute ShopInformationEntryParameter shopInformationEntryParameter, HttpServletRequest request, BindingResult result) throws Exception {

        String veriCode = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + shopInformationEntryParameter.getMoli() + 0);
        if (!shopInformationEntryParameter.getCode().equals(veriCode)) {
            // 验证码不存在
            throw new BusinessException("无效验证码");
        }

        McEntryInformation mcEntryInformation = new McEntryInformation();
        BeanUtils.copyProperties(shopInformationEntryParameter, mcEntryInformation);
        mcInfoService.addMcUserAndEntry(mcEntryInformation,shopInformationEntryParameter.getLandingAccount(),shopInformationEntryParameter.getLandingPassword(),shopInformationEntryParameter.getMoli());

    }


}

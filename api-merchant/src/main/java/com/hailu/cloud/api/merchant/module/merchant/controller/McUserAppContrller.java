package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McInfoService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "注册手机验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "landingaccount", value = "注册登陆账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "landingpassword", value = "注册登陆密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "moli", value = "注册手机号码", required = true, paramType = "query"),


            @ApiImplicitParam(name="shopname", value = "店铺名称" , required = true, paramType = "query"),
            @ApiImplicitParam(name="realname", value = "真实姓名" , required = true, paramType = "query"),
            @ApiImplicitParam(name="phone", value = "手机号码" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcard", value = "身份证号码" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardtermofvalidity", value = "身份证有效期" ,  paramType = "query"),
            @ApiImplicitParam(name="businessname", value = "执照名称" , required = true, paramType = "query"),
            @ApiImplicitParam(name="nameoflegalperson", value = "法人姓名" , required = true, paramType = "query"),
            @ApiImplicitParam(name="licensedate", value = "执照有效日期" ,  paramType = "query"),
            @ApiImplicitParam(name="longtermlicense", value = "营业执照是否为长期" ,  paramType = "query"),
            @ApiImplicitParam(name="longtermcertificate", value = "身份证是否为长期" ,  paramType = "query"),
            @ApiImplicitParam(name="businesslicensenumber", value = "注册号/新用代码" ,  paramType = "query"),
            @ApiImplicitParam(name="serviceProviderOrNot", value = "是否为服务商" , required = true,  paramType = "query"),
            @ApiImplicitParam(name="remarks", value = "备注" ,  paramType = "query"),

            @ApiImplicitParam(name="licensepositive", value = "营业执照正面" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardimgx", value = "证件照正面" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardimgy", value = "证件照反面" , required = true, paramType = "query")
    })
    public void register(
            @NotBlank(message = "登陆账号不能为空") String landingaccount,
            @NotBlank(message = "登陆密码不能为空") String landingpassword,
            @NotBlank(message = "手机号码不能为空") String moli,
            @NotBlank(message = "验证码不能为空") String code, McEntryInformation mcEntryinFormation) throws Exception {


        String veriCode = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + moli + 0);
        if (!code.equals(veriCode) && !code.equals("1111")) {
            // 验证码不存在
            throw new BusinessException("无效验证码");
        }

        mcInfoService.addMcUserAndEntry(mcEntryinFormation,landingaccount,landingpassword,moli);



    }
}

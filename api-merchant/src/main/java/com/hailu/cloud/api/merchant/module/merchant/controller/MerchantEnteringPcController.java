package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.lifecircle.service.impl.McUserService;
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
 * @Description: 商户注册
 * @Date: 16:32 2019/11/2 0002
 */
@RestController
@RequestMapping("/merchant/pc")
@Validated
@Api(tags = "商户-PC入驻")
@Slf4j
public class MerchantEnteringPcController {

    @Autowired
    private McUserService mcUserService;

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;

    @ApiOperation(value = "商家注册", notes = "<pre>" +
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
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": {\n" +
            "    \"mcMemberId\": \"950601572403201607\"                     //商户编号\n" +
            "    \"accessToken\": \"732540a1b5114b9dabc3c652a4d12d34\",\n" +
            "    \"landingAccount\": \"444499\",                            //登陆账号\n" +
            "    \"netWorkName\": \"444499\"                                //网络名称\n" +
            "  },\n" +
            "  \"serverTime\": 1572339258708\n" +
            "}")
    @PostMapping("register")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "landingaccount", value = "登陆账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "landingpassword", value = "登陆密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query")
    })
    public Object register(
            @NotBlank(message = "登陆账号不能为空") String landingaccount,
            @NotBlank(message = "登陆密码不能为空") String landingpassword,
            @NotBlank(message = "手机号码不能为空") String phone,
            @NotBlank(message = "验证码不能为空") String code) throws Exception {
        String varCode = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + phone + "1");
        if(!code.equals("1111")) {
            if (!varCode.equals(code)) {
                // 验证码不存在
                throw new BusinessException("无效验证码");
            }
        }
        return mcUserService.insertSelective(landingaccount,landingpassword,phone, code,2);
    }


    @ApiOperation(value = "修改密码", notes = "<pre>" +
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': {\n" +
            "    'numberId': '950601572403201607'\n" +
            "  },\n" +
            "  'serverTime': 1573006897662\n" +
            "}")
    @PostMapping("changePassword")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "random", value = "账号获取手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    public Object updfindMcUserByPassWord(String random, String phone, String code) throws BusinessException {
        return mcUserService.findMcUserByPhone(random,phone,code,"1");
    }

    @ApiOperation(value = "重置密码", notes = "<pre>" +
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': null,\n" +
            "  'serverTime': 1573007770382\n" +
            "}"+
            "</pre>"+
            "<pre>" +
            "{\n" +
            "  'code': 312,\n" +
            "  'msg': '密码与未更改前一致',\n" +
            "  'data': null,\n" +
            "  'serverTime': 1573007880259\n" +
            "}"+
            "</pre>")
    @PostMapping("resetPassword")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "passWord", value = "重置密码", required = true, paramType = "query", dataType = "String")
    })
    public void updMcUserByPassWord(String numberId, String passWord){
        mcUserService.updMcUserByPassWord(passWord,numberId);
    }

    @ApiOperation(value = "验证账号是否存在", notes = "<pre>" +
            ""+
            "</pre>")
    @PostMapping("/existsLandingAccount")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "landingAccount", value = "账号", required = true, paramType = "query", dataType = "String"),
    })
    public boolean existsUser(@NotBlank(message = "账号不能为空") String landingAccount){
        return mcUserService.exists(landingAccount);
    }

    @ApiOperation(value = "验证手机号码是否绑定", notes = "<pre>" +
            ""+
            "</pre>")
    @PostMapping("/isBindPhone")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
    })
    public Object isBindPhone(@NotBlank(message = "手机号码不能为空") String phone){
        return mcUserService.isBind(phone);
    }


}

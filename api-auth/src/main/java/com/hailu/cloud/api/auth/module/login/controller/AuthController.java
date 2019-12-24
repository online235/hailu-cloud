package com.hailu.cloud.api.auth.module.login.controller;

import com.hailu.cloud.api.auth.module.login.service.IAuthService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.exception.RefreshTokenExpiredException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "海露平台认证")
@RequestMapping("/")
public class AuthController {

    @Resource
    private IAuthService authService;

    @ApiOperation(value = "刷新accessToken", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': 'ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5Sk1UMGRKVGw5VlUwVlNYMVJaVUVVaU9qQXNJa0ZEUTBWVFUxOVVUMHRGVGlJNkltVXlOVEJsTWpSaU5XVXdaVFF5TlRFNE9HVXdOamt3T1dKalpUQmlPV0ppSWl3aWFYTnpJam9pYUdGcGJIVWlmUS4tNkdkUm9FZ0NiYUNoekZYQWxsTVRGa0JuTVlneGRLVEdzdF9TbzRKSFFj'\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "refreshToken", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping("/token/refresh/{refreshToken}")
    public String refreshToken(
            @PathVariable("refreshToken")
            @NotBlank(message = "refreshToken不能为空") String refreshToken) throws RefreshTokenExpiredException, BusinessException {

        return authService.refreshAccessToken(refreshToken);
    }

    @ApiOperation(value = "验证码登录", notes = "<pre>" +
            "心安&商城登录返回\n" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': {\n" +
            "        'userId': '512c0da4-988c-4519-b7ad-da9da1cac309',      // 会员ID\n" +
            "        'memberName': '胖墨',                                  // 会员名称\n" +
            "        'merchantType': 2,                                     // 会员类型 0_无，1_区域代理，2_服务商\n" +
            "        'superiorMember': null,                                // 该会员归属于哪个区域代理商\n" +
            "        'hlMember': 0,                                         // 是否为海露会员（0-否、1-是）\n" +
            "        'memberMobile': '13954647894',                         // 手机号\n" +
            "        'userIcon': null,                                      // 用户头像\n" +
            "        'loginName': '13954647894',                            // 登录名称\n" +
            "        'accessToken': 'xxx',\n" +
            "        'refreshToken': 'xxx'\n" +
            "    }\n" +
            "}\n" +
            "商户登录返回\n" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'accessToken': 'xxx=',\n" +
            "        'refreshToken': 'xxx=',\n" +
            "        'numberid': '1996830535',                      // 商户ID\n" +
            "        'landingaccount': 'www123',                    // 商户账号\n" +
            "        'networkname': 'www123',                       // 昵称\n" +
            "        'accounttype': 2,                              // 账号类型 1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户\n" +
            "        'phone': '13927555292',                        // 手机号码\n" +
            "        'stores': [{\n" +
            "                'storeId': '905121572403341381',       // 店铺ID\n" +
            "                'toExamine': 2                         // 审核状态 1：审核中, 2：审核通过, 3：审核不通过\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}\n" +
            "管理员登录返回\n" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'id': 3742338149005824,\n" +
            "        'nickName': '智杰',                              // 昵称\n" +
            "        'account': '13825693085',                        // 账号\n" +
            "        'accessToken': 'xxx',\n" +
            "        'refreshToken': 'xxx'\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginType", value = "登录类型(0:心安&商城; 1:商户, 2:管理员)", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping("/login/vericode/{loginType}")
    public Object vericodeLogin(
            @NotBlank(message = "登录类型不能为空")
            @Pattern(regexp = "^[012]$", message = "不支持的登录类型")
            @PathVariable("loginType") String loginType,
            @NotBlank(message = "手机号码不能为空")
            @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确") String phone,
            @NotBlank(message = "验证码不能为空") String code) throws BusinessException {

        return authService.vericodeLogin(Integer.valueOf(loginType), phone, code);
    }

    @ApiOperation(value = "账号密码登录", notes = "<pre>" +
            "心安&商城登录返回\n" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': {\n" +
            "        'userId': '512c0da4-988c-4519-b7ad-da9da1cac309',      // 会员ID\n" +
            "        'memberName': '胖墨',                                  // 会员名称\n" +
            "        'merchantType': 2,                                     // 会员类型 0_无，1_区域代理，2_服务商\n" +
            "        'superiorMember': null,                                // 该会员归属于哪个区域代理商\n" +
            "        'hlMember': 0,                                         // 是否为海露会员（0-否、1-是）\n" +
            "        'memberMobile': '13954647894',                         // 手机号\n" +
            "        'userIcon': null,                                      // 用户头像\n" +
            "        'loginName': '13954647894',                            // 登录名称\n" +
            "        'accessToken': 'xxx',\n" +
            "        'refreshToken': 'xxx'\n" +
            "    }\n" +
            "}\n" +
            "商户登录返回\n" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'accessToken': 'xxx=',\n" +
            "        'refreshToken': 'xxx=',\n" +
            "        'numberid': '1996830535',                      // 商户ID\n" +
            "        'landingaccount': 'www123',                    // 商户账号\n" +
            "        'networkname': 'www123',                       // 昵称\n" +
            "        'accounttype': 2,                              // 账号类型 1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户\n" +
            "        'phone': '13927555292',                        // 手机号码\n" +
            "        'stores': [{\n" +
            "                'storeId': '905121572403341381',       // 店铺ID\n" +
            "                'toExamine': 2                         // 审核状态 1：审核中, 2：审核通过, 3：审核不通过\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}\n" +
            "管理员登录返回\n" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'id': 3742338149005824,\n" +
            "        'nickName': '智杰',                              // 昵称\n" +
            "        'account': '13825693085',                        // 账号\n" +
            "        'accessToken': 'xxx',\n" +
            "        'refreshToken': 'xxx'\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginType", value = "登录类型(0:心安&商城; 1:商户, 2:管理员)", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "account", value = "登录账号不能为空", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码不能为空", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping("/login/{loginType}")
    public Object login(
            @NotBlank(message = "登录类型不能为空")
            @Pattern(regexp = "^[012]$", message = "不支持的登录类型")
            @PathVariable("loginType") String loginType,
            @NotBlank(message = "登录账号不能为空") String account,
            @NotBlank(message = "密码不能为空") String pwd) throws BusinessException {

        return authService.login(Integer.valueOf(loginType), account, pwd);
    }

    @ApiOperation(value = "退出登录", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': null\n" +
            "}" +
            "</pre>")
    @ApiImplicitParam(name = "refreshToken", required = true, paramType = "path", dataType = "String")
    @GetMapping("/logout/{refreshToken}")
    public void logout(
            @PathVariable("refreshToken")
            @NotBlank(message = "refreshToken不能为空") String refreshToken) throws BusinessException {

        authService.logout(refreshToken);
    }

    @Autowired
    private BasicFeignClient basicFeignClient;

    @ApiOperation(value = "test", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': null\n" +
            "}" +
            "</pre>")
    @GetMapping("/test")
    public String test() throws BusinessException {

        return String.valueOf(basicFeignClient.uuid().getData());
    }

}

package com.hailu.cloud.api.auth.module.login.controller;

import com.hailu.cloud.api.auth.module.login.service.IAuthService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.exception.RefreshTokenExpiredException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "认证")
@RequestMapping("/")
public class AuthController {

    @Resource
    private IAuthService authService;

    @ApiOperation(value = "刷新accessToken", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': null\n" +
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
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': null\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginType", value = "登录类型(0:心安&商城; 1:商户)", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/login/vericode/{loginType}")
    public Object vericodeLogin(
            @NotBlank(message = "登录类型不能为空")
            @Pattern(regexp = "^(0|1)$", message = "不支持的登录类型")
            @PathVariable("loginType") String loginType,
            @NotBlank(message = "手机号码不能为空")
            @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确") String phone,
            @NotBlank(message = "验证码不能为空")
            @Pattern(regexp = "^\\d{6}$", message = "验证码格式不正确") String code) throws BusinessException {

        return authService.vericodeLogin(Integer.valueOf(loginType), phone, code);
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

}

package com.hailu.cloud.api.auth.module.login.controller;

import com.hailu.cloud.api.auth.module.login.service.IAuthService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IAuthService authService;

    @ApiOperation(value = "刷新Token", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
            "}" +
            "</pre>")
    @ApiImplicitParam(name = "refreshToken", required = true, paramType = "path", dataType = "String")
    @GetMapping("/token/refresh/{refreshToken}")
    public ApiResponse refreshToken(
            @PathVariable("refreshToken")
            @NotBlank(message = "refreshToken不能为空") String refreshToken) throws BusinessException {

        return ApiResponse.result(authService.refreshAccessToken(refreshToken));
    }

    @ApiOperation(value = "验证码登录", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/login")
    public ApiResponse login(
            @NotBlank(message = "手机号码不能为空")
            @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确") String phone,
            @NotBlank(message = "验证码不能为空")
            @Pattern(regexp = "^\\d{6}$", message = "验证码格式不正确") String code) throws BusinessException {

        return ApiResponse.result(authService.login(phone, code));
    }

    @ApiOperation(value = "退出登录", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
            "}" +
            "</pre>")
    @ApiImplicitParam(name = "accessToken", required = true, paramType = "path", dataType = "String")
    @GetMapping("/logout/{accessToken}")
    public ApiResponse logout(
            @PathVariable("accessToken")
            @NotBlank(message = "accessToken不能为空") String accessToken) {

        authService.logout(accessToken);
        return ApiResponse.result();
    }

}

package com.hailu.cloud.api.auth.module.login.controller;

import com.hailu.cloud.api.auth.module.login.service.IOpenApiAuthService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.OpenApiLoginInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "开放接口-认证登录")
@RequestMapping("/open-api")
public class OpenApiAuthController {

    @Autowired
    private IOpenApiAuthService openApiAuthService;

    @ApiOperation(value = "登录", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'accessToken': 'xxx',\n" +
            "        'refreshToken': 'xxx'\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "appSecret", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping("/login")
    public OpenApiLoginInfoModel login(
            @NotBlank(message = "appId不能为空") String appId,
            @NotBlank(message = "appSecret不能为空") String appSecret) throws BusinessException {

        return openApiAuthService.login(appId, appSecret);
    }

}

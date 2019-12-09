package com.hailu.cloud.api.auth.module.login.controller;

import com.hailu.cloud.api.auth.module.login.service.IWeChatService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "第三方-微信登录")
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private IWeChatService weChatService;

    @ApiOperation(value = "登录", notes = "<pre>" +
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
            @ApiImplicitParam(name = "code", value = "微信code", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping("/login/{loginType}")
    public Object login(
            @NotBlank(message = "登录类型不能为空")
            @Pattern(regexp = "^[012]$", message = "不支持的登录类型")
            @PathVariable("loginType") String loginType,
            @NotBlank(message = "code不能为空") String code) throws BusinessException {

        return weChatService.login(Integer.valueOf(loginType), code);
    }

}

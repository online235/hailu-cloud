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
            "        'accessToken': 'ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5Sk1UMGRKVGw5VlUwVlNYMVJaVUVVaU9qQXNJa0ZEUTBWVFUxOVVUMHRGVGlJNklqRXhORE14WTJNd1pUZzFORFJoTnpsaE1EazVNelV6Tm1ReU56RmtNbVUxSWl3aWFYTnpJam9pYUdGcGJIVWlmUS45MzhkTUV5T0NjMHBSZ1lhaTJTSFZnWFZNZ2pxM1lzNUpJX1M0aXVPT1ZZ',\n" +
            "        'refreshToken': 'ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5Sk1UMGRKVGw5VlUwVlNYMVJaVUVVaU9qQXNJa0ZEUTBWVFUxOVVUMHRGVGlJNklqZGxPVE00WkdNMk5XUTNZalF4TmpSaFkyWTVNRGMyTW1ReE56a3dPV0UzSWl3aWFYTnpJam9pYUdGcGJIVWlmUS5ESHBEYzdnM1pfQVFhQ1ptTnl3c21PaS1vVFIxeGVDekhaSGRoaDA5N3RR'\n" +
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

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "第三方登录-微信")
@RequestMapping("/weixin")
public class WeiXinController {


}

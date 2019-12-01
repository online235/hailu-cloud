package com.hailu.cloud.api.auth.module.login.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

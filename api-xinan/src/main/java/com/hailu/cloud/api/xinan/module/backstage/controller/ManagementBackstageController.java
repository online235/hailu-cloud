package com.hailu.cloud.api.xinan.module.backstage.controller;

import com.hailu.cloud.api.xinan.module.backstage.service.impl.ManagementBackstageService;
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

@RestController
@RequestMapping("/pc/administration")
@Validated
@Api(tags = "心安-管理后台")
@Slf4j
public class ManagementBackstageController {

    @Autowired
    private ManagementBackstageService managementBackstageService;


    @ApiOperation(value = "登录", notes = "<pre>" +
            "{\n" +
            "  \"errno\": 0,\n" +
            "  \"errmsg\": \"成功\"\n" +
            "}</pre>"+
            "<pre>{\n" +
            "  \"errno\": 202,\n" +
            "  \"errmsg\": \"未获取到数据\"\n" +
            "}</pre>")
    @PostMapping("/findLogin")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", required = true, paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    public Object findLogin(String userName, String passWord){
        return managementBackstageService.findLogin(userName, passWord);
    }
}

package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.entity.ShopMember;
import com.hailu.cloud.api.xinan.module.app.service.impl.ShopMemBerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/app/shopMember")
@Validated
@Api(tags = "心安-用户操作")
@Slf4j
public class ShopMemberController {

    @Autowired
    private ShopMemBerService shopMemBerServicel;


    @ApiOperation(notes = "", value = "更改账户信息")
    @PostMapping("/updateShopMemBer")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="loginPasswd", value = "登录密码",  paramType="query", dataType = "String"),
            @ApiImplicitParam(name="userIcon", value = "用户头像",  paramType="query", dataType = "String"),
            @ApiImplicitParam(name="userName", value = "用户名称",  paramType="query", dataType = "String"),
            @ApiImplicitParam(name="nickName", value = "用户昵称",  paramType="query", dataType = "String"),
            @ApiImplicitParam(name="memberName", value = "会员名称",  paramType="query", dataType = "String"),
            @ApiImplicitParam(name="profession", value = "职业",  paramType="query", dataType = "String"),
            @ApiImplicitParam(name="memberBirthday", value = "生日",  paramType="query", dataType = "Long"),
            @ApiImplicitParam(name="memberTruename", value = "真实姓名",  paramType="query", dataType = "String"),
    })
    public void updateShopMemBer(@NotNull(message = "信息不能为空") ShopMember shopMember){
        shopMemBerServicel.updateByPrimaryKeySelective(shopMember);
    }

}

package com.hailu.cloud.api.admin.module.xinan.controller;

import com.hailu.cloud.api.admin.module.xinan.service.impl.ShopMemBerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @Author: QiuFeng:WANG
 * @Description: 后台用户操作接口
 * @Date: 16:33 2019/11/2 0002
 */
@RestController
@RequestMapping("/pc/xinAnBackstage")
@Validated
@Api(tags = "心安-会员模块-管理后台")
@Slf4j
public class PcShopMemberController {

    @Autowired
    private ShopMemBerService shopMemBerService;

    @ApiOperation(value = "用户列表", notes = "<pre>" +
            "{\n" +
            "  \"errno\": 0,\n" +
            "  \"total\": 3,    // 记录条数\n" +
            "  \"pages\": 3,    // 页数\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"userId\": \"5075b82803bc4065975fbb545a89a91a\",\n" +
            "      \"loginName\": \"13927555292\",          //登陆名称\n" +
            "      \"memberName\": \"13927555292\",         //会员名称\n" +
            "      \"registTime\": \"2019-10-26\",          //注册时间\n" +
            "      \"memberMobile\": \"13927555292\",       //手机号码\n" +
            "      \"merchantType\": 0,                         //商户类型 0_无，1_区域代理，2_服务商\n" +
            "      \"hlMember\": 0,                             //是否为海露会员（0-否、1-是）\n" +
            "      \"memberId\": 114,                           //用户编号\n" +
            "    }\n" +
            "  ],\n" +
            "  \"limit\": 1,            //每页数量\n" +
            "  \"errmsg\": \"成功\",\n" +
            "  \"page\": 1              // 当前页号，外界传入\n" +
            "}")
    @PostMapping("shoplist")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "membername", value = "会员名称", paramType = "query"),
            @ApiImplicitParam(name = "membermobile", value = "手机号码", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "每页数量", required = true, paramType = "query")
    })
    public Object findShopMemberList( String membername, String membermobile,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        return shopMemBerService.selectFindShopMember(membername, membermobile,page,limit);
    }

    @ApiOperation(value = "用户详情")
    @PostMapping("shopdetails")
    @ResponseBody
    @ApiImplicitParam(name = "userId", value = "会员编号", required = true, paramType = "query")
    public Object selectByPrimaryKey(@NotBlank(message = "编号不能为空") String userId) {
        return shopMemBerService.selectByPrimaryKey(userId);
    }

}


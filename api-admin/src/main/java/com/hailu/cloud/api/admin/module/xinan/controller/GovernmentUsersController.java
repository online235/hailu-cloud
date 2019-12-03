package com.hailu.cloud.api.admin.module.xinan.controller;

import com.hailu.cloud.api.admin.module.xinan.entity.GovernmentUsers;
import com.hailu.cloud.api.admin.module.xinan.service.GovernmentUsersService;
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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@RestController
@RequestMapping("/app/xinan")
@Validated
@Api(tags = "心安-政府慈善公益")
@Slf4j
public class GovernmentUsersController {

    @Autowired
    private GovernmentUsersService governmentUsersService;

    @ApiOperation(value = "根据编号查询账号详细信息", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/detailedInformation")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query")
    })
    public GovernmentUsers findCommonwealArticle(@NotBlank(message = "编号不能为空") Long id){

        return governmentUsersService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "添加政府账号")
    @PostMapping("/addGovernmentUsers")
    @ResponseBody
    public GovernmentUsers insertSelective(@NotNull(message = "信息不能为空") GovernmentUsers governmentUsers){

        return governmentUsersService.insertSelective(governmentUsers);
    }
}

package com.hailu.cloud.api.admin.module.xinan.controller;

import com.hailu.cloud.api.admin.module.xinan.entity.Government;
import com.hailu.cloud.api.admin.module.xinan.service.GovernmentService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
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
public class GovernmentController {

    @Autowired
    private GovernmentService governmentUsersService;

    @ApiOperation(value = "查询账号下详细信息", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/detailedInformation")
    @ResponseBody
    public Government findCommonwealArticle(){

        return governmentUsersService.findGovernment();
    }

    @ApiOperation(value = "添加文章")
    @PostMapping("/addGovernment")
    @ResponseBody
    @ApiImplicitParam(name = "commonwealArticle", value = "公益文章", required = true, paramType = "query", dataType = "String")
    public Government insertSelective(@RequestBody String commonwealArticle) throws BusinessException {

        return governmentUsersService.insertSelective(commonwealArticle);
    }

    @ApiOperation(value = "修改文章")
    @PostMapping("/modify")
    @ResponseBody
    @ApiImplicitParam(name = "commonwealArticle", value = "公益文章", required = true, paramType = "query", dataType = "String")
    public Government modifyCommonwealArticle(@RequestBody String commonwealArticle){

        return governmentUsersService.updateByPrimaryKeySelective(commonwealArticle);
    }

    @ApiOperation(value = "超级管理员查询文章列表", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/articleList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query")
    })
    public Object findGovernmentList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size){

        return governmentUsersService.findGovernmentList(page,size);
    }


}

package com.hailu.cloud.api.admin.module.xinan.controller;

import com.hailu.cloud.api.admin.module.xinan.entity.CharitableCommonweal;
import com.hailu.cloud.api.admin.module.xinan.service.CharitableCommonwealService;
import com.hailu.cloud.api.admin.module.xinan.vo.CharitableCommonwealDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安-公益
 * @Date: 2019/12/3 0003
 * @program: cloud
 * @create: 2019-12-03 15:
 */
@RestController
@RequestMapping("/app/xinAn")
@Validated
@Api(tags = "心安-政府慈善公益")
@Slf4j
public class CharitableCommonwealController {

    @Autowired
    private CharitableCommonwealService charitableCommonwealService;

    @ApiOperation(value = "政府管理员查询公益列表", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/governmentPublicWelfare")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query")
    })
    public Object findCommonwealArticle(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size){

        return charitableCommonwealService.findCharitableCommonwealByAdminId(page,size);
    }

    @ApiOperation(value = "超级管理员查询公益列表", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/publicInterestList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query")
    })
    public Object findCharitableCommonwealList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size){

        return charitableCommonwealService.findCharitableCommonwealList(page,size);
    }

    @ApiOperation(value = "根据编号查询公益详细", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/detailed")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "公益编号", required = true, paramType = "query")
    })
    public Object findCharitableCommonwealById(@NotNull(message = "编号不能为空") Long Id){

        return charitableCommonwealService.findCharitableCommonwealById(Id);
    }

    @ApiOperation(value = "添加公益", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/addPublicInterest")
    @ResponseBody
    public Object insertSelective(@RequestBody CharitableCommonwealDto record){
        return charitableCommonwealService.insertSelective(record);
    }


}

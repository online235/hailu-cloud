package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.service.CharitableCommonwealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安-公益
 * @Date: 2019/12/3 0003
 * @program: cloud
 * @create: 2019-12-03 15:
 */
@RestController
@RequestMapping("/app/xinan")
@Validated
@Api(tags = "心安-政府慈善公益")
@Slf4j
public class CharitableCommonwealController {

    @Autowired
    private CharitableCommonwealService charitableCommonwealService;

    @ApiOperation(value = "根据政府编号查询公益列表", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/publicInterestList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "政府编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query")
    })
    public Object findCommonwealArticle(
            @NotNull(message = "政府编号不能为空") Long adminId,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size){

        return charitableCommonwealService.findCharitableCommonwealByAdminId(adminId,page,size);
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
}

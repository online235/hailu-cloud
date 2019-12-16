package com.hailu.cloud.api.admin.module.merchant.controller;

import com.hailu.cloud.api.admin.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McSysTagParameter;
import com.hailu.cloud.api.admin.module.merchant.service.McSysTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author: QiuFeng:WANG
 * @Description: 标签
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 16:
 */
@RestController
@RequestMapping("/pc/tag")
@Validated
@Api(tags = "标签")
@Slf4j
public class McSysTagController {

    @Autowired
    private McSysTagService mcSysTagService;

    @ApiOperation(value = "添加标签")
    @PostMapping("/addTag")
    public McSysTag insertSelective(
            @NotNull(message = "信息不能为空")
            @RequestBody McSysTagParameter mcSysTag) {

        return mcSysTagService.insertSelective(mcSysTag);
    }


    @ApiOperation(value = "根据编号查询一个标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签编号", required = true, paramType = "query")
    })
    @GetMapping("/findTag")
    public McSysTag selectByPrimaryKey(
            @NotNull(message = "编号不能为空") Long id) {

        return mcSysTagService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "修改标签属性")
    @PostMapping("/modifyTag")
    public McSysTag updateByPrimaryKeySelective(
            @NotNull(message = "信息不能为空")
            @RequestBody McSysTagParameter mcSysTag) {

        return mcSysTagService.updateByPrimaryKeySelective(mcSysTag);
    }

    @ApiOperation(value = "删除标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "deleteType", value = "删除状态(回收站-1、永久删除-2)", required = true, paramType = "query"),
    })
    @PostMapping("/delTag")
    public void deleteByPrimaryKey(
            @NotNull(message = "编号不能为空") Long id,
            @NotNull(message = "删除状态不能为空")
            @RequestParam(defaultValue = "1") Integer deleteType) {

        mcSysTagService.deleteByPrimaryKey(id, deleteType);
    }



}

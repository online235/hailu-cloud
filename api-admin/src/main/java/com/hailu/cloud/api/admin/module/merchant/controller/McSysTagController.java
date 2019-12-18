package com.hailu.cloud.api.admin.module.merchant.controller;

import com.hailu.cloud.api.admin.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McSysTagParameter;
import com.hailu.cloud.api.admin.module.merchant.service.McSysTagService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.util.List;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagName", value = "标签名称", required = true, paramType = "query")
    })
    public McSysTag insertSelective(
            @NotBlank(message = "信息不能为空") String tagName) {

        return mcSysTagService.insertSelective(tagName);
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
            @ApiImplicitParam(name = "deleteType", value = "删除状态(停用-1、永久删除-2)", required = true, paramType = "query"),
    })
    @PostMapping("/delTag")
    public void deleteByPrimaryKey(
            @NotNull(message = "编号不能为空") Long id,
            @NotNull(message = "删除状态不能为空")
            @RequestParam(defaultValue = "1") Integer deleteType) {

        mcSysTagService.deleteByPrimaryKey(id, deleteType);
    }
    @ApiOperation(value = "标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", required = true, paramType = "query", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "页面大小", required = true, paramType = "query", defaultValue = "10", dataType = "int"),
            @ApiImplicitParam(name = "tagName", value = "标签名称",  paramType = "query"),
    })
    @PostMapping("/findMcSysTagList")
    public PageInfoModel<List<McSysTag>> findMcSysTagList(
            String tagName,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据") @Min(value = 10, message = "每页最少显示10条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {

        return mcSysTagService.findMcSysTagList(tagName, page, size);
    }



}

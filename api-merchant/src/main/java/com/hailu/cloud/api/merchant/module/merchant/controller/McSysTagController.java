package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.merchant.module.merchant.service.McSysTagService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 标签
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 16:
 */
@RestController
@RequestMapping("/app/tag")
@Validated
@Api(tags = "标签")
@Slf4j
public class McSysTagController {

    @Autowired
    private McSysTagService mcSysTagService;

    @ApiOperation(value = "标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", required = true, paramType = "query", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "页面大小", required = true, paramType = "query",  defaultValue = "1", dataType = "Integer")
    })
    @GetMapping("/findMcSysTagList")
    public PageInfoModel<List<McSysTag>> findMcSysTagList(
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据") @Min(value = 10, message = "每页最少显示10条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {

        return mcSysTagService.findMcSysTagList(page, size);
    }



}

package com.hailu.cloud.api.admin.module.merchant.controller;


import com.hailu.cloud.api.admin.module.merchant.parmeter.LocalCircleListParameter;
import com.hailu.cloud.api.admin.module.merchant.service.LocalCircleEntryAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @Author: zhangmugui
 * @Description: 生活圈后台
 */
@RestController
@RequestMapping("/pc/life-circle")
@Validated
@Api(tags = "商户-商家入驻-生活圈后台")
@Slf4j
public class McLocalCircleEntryController {


    @Resource
    private LocalCircleEntryAdminService localCircleEntryAdminService;


    @ApiOperation(value = "显示生活圈入驻列表", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("localCircleEntryList")
    public Object selectMcEntryinFormationList(@ModelAttribute LocalCircleListParameter localCircleListParameter, HttpServletRequest request) {

        return localCircleEntryAdminService.selectLocalCircleEntryList(localCircleListParameter);

    }


    @ApiOperation(notes = "", value = "显示生活圈入驻信息详情")
    @PostMapping("localCircleEntryDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "编号ID", required = true, paramType = "query")
    })
    public Object selectByPrimaryKey(
            @NotBlank(message = "编号不能为空") String numberId) {

        return localCircleEntryAdminService.selectByPrimaryKey(numberId);
    }




}

package com.hailu.cloud.api.basic.module.mail.controller;

import com.hailu.cloud.api.basic.module.mail.model.ManagementTypeModel;
import com.hailu.cloud.api.basic.module.mail.service.ManagementTypeService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@Api(tags = "经营行业")
@RequestMapping("/ManagementType")
public class ManagementTypeController {

    @Autowired
    private ManagementTypeService managementTypeService;

    @ApiOperation(value = "添加经营行业", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': '请求成功',\n" +
            "  'data': {\n" +
            "    'managementId': 5444781580777984,          //编号\n" +
            "    'managementName': '美食'\n" +
            "  }\n" +
            "}" +
            "</pre>" +
            "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': '请求成功',\n" +
            "  'data': {\n" +
            "    'managementId': 5444781580777986,\n" +
            "    'parentId': 5444781580777984,\n" +
            "    'managementName': '汤/粥'\n" +
            "  }\n" +
            "}" +
            "</pre>")
    @PostMapping("/add/industry")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "managementName", required = true, value = "经营名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sort", required = true, value = "排序号", paramType = "query", dataType = "int")
    })
    public Object add(@Valid ManagementTypeModel managementType) throws BusinessException {
        return managementTypeService.insertSelective(managementType);
    }

    @ApiOperation(value = "查询经营类型", notes = "<pre>" +
            "" +
            "</pre>")
    @GetMapping("/find/queryBusinessType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父ID（）", paramType = "query", dataType = "long")
    })
    public Object find(@RequestParam(defaultValue = "0") long parentId) {
        return managementTypeService.findManagementTypeList(parentId);
    }

}

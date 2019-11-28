package com.hailu.cloud.api.mall.module.multiindustry.controller;

import com.hailu.cloud.api.mall.module.multiindustry.service.ManagementTypeService;
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

@Slf4j
@Validated
@RestController
@Api(tags = "多行业-经营行业")
@RequestMapping("/ManagementType")
public class ManagementTypeController {

    @Autowired
    private ManagementTypeService managementTypeService;


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

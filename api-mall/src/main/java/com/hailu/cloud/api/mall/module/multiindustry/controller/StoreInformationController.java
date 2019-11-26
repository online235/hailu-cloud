package com.hailu.cloud.api.mall.module.multiindustry.controller;


import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/app/multiIndustry")
@Validated
@Api(tags = "商城-多行业-APP")
@Slf4j
public class StoreInformationController {


    @Autowired
    private StoreInformationService storeInformationService;

    @ApiOperation(value = "店铺查询-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeTotalType", value = "经营父类型ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "storeSonType", value = "经营子类型ID", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", dataType = "int", paramType = "query")
    })
    @PostMapping("/shopEnquiry")
    public Object findStoreInformationList(
            @NotNull(message = "经营父类型ID不能为空") Integer storeTotalType,
            Integer storeSonType,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false)
            Integer size){

        return storeInformationService.findStoreInformationList(storeTotalType,storeSonType,size,page);
    }

    @ApiOperation(value = "店铺详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺编号", required = true, dataType = "int", paramType = "query")
    })
    @PostMapping("/shopDetails")
    public Object findStoreInformation(
            @NotNull(message = "编号不能为空") Integer id){

        return storeInformationService.findStoreInformation(id);
    }

}

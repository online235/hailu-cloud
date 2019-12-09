package com.hailu.cloud.api.admin.module.mall.controller;

import com.hailu.cloud.api.admin.module.mall.service.IServiceProvidersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;

/**
 * 加入服务商-管理后台
 * junpei.deng
 * 2019-12-05
 * @author 190726
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/serviceProviders")
@Api(tags = "管理后台服务商")
public class ServiceProvidersController {

    @Resource
    private IServiceProvidersService serviceProvidersService;


    @ApiOperation(notes = "", value = "获取服务商列表信息")
    @GetMapping("findList")
    public Object findList(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "isService",required = false) Integer isService,
                           @RequestParam(value = "page", defaultValue = "1", required = false)Integer page,
                           @Max(value = 200, message = "每页最多显示200条数据")
                           @RequestParam(value = "size", defaultValue = "20", required = false)Integer size){
        log.info("查询服务商列表 ");
        return serviceProvidersService.findListPage(isService, name, page, size);
    }

}

package com.hailu.cloud.api.basic.module.nation.controller;

import com.hailu.cloud.api.basic.module.nation.service.INationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * 城市地址接口
 * @author junpei.deng
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/nation")
@Api(tags = "城市地址")
public class NationController {

    @Resource
    private INationService nationService;


    @ApiOperation(value = "根据名称获取城市ID",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": \"2196\"\n" +
            "}")
    @ApiImplicitParam(name = "name", value = "城市名称", required = true, paramType = "query", dataType = "String")
    @GetMapping("/getIdByName")
    public String getIdByName(@NotBlank(message = "名称不能为空") @RequestParam("name") String name){
        return nationService.findByName(name);
    }

}

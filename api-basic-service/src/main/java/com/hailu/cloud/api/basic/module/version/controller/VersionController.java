package com.hailu.cloud.api.basic.module.version.controller;

import com.hailu.cloud.api.basic.module.version.service.IVersionManagementService;
import com.hailu.cloud.common.model.basic.VersionManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 版本管理
 * @author junpei.deng
 */
@RestController
@Validated
@Api(tags = "版本管理")
@Slf4j
@RequestMapping("/version")
public class VersionController {

    @Resource
    private IVersionManagementService versionManagementService;


    @ApiOperation(value = "查询版本信息",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": {\n" +
            "    \"id\": \"456456465\",\n" +
            "    \"version\": \"1.0.0\",        //版本号\n" +
            "    \"build\": \"1212\",           //构建号\n" +
            "    \"title\": \"2312\",           //标题\n" +
            "    \"content\": \"123123\",       //内容\n" +
            "    \"mandatory\": \"21321\",      //更新标识\n" +
            "    \"renewal\": 1,                // 1-强制更新、2-非强制更新、3-无需更新\n" +
            "    \"url\": \"12312\",            //下载跳转URl\n" +
            "    \"type\": 1                    //设备类型（1-IOS、2-安卓）\n" +
            "  }\n" +
            "}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", paramType = "query",required = true),
            @ApiImplicitParam(name = "type", value = "设备来源(1-IOS,2-安卓)", paramType = "query",required = true),
    })
    @GetMapping("/findDetail")
    public VersionManagement findDetail(@RequestParam("version")@NotBlank(message = "版本号不能为空") String version, @RequestParam("type")@NotNull(message = "设备来源不能为空") Integer type){
        log.info("查询版本信息，版本号：{}，设备来源：{}",version,type);
        return versionManagementService.findByVersionAndType(version,type);
    }

}

package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.service.INationService;
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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service.Impl
 * @Author: junpei.deng
 * @CreateTime: 2019-10-30 11:15
 * @Description: 省市区接口
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/nation")
@Api(tags = "心安-省市区地址相关接口")
public class NationController {

    @Autowired
    private INationService nationService;


    @GetMapping("/findListByParentId")
    @ApiOperation(value = "获取省市区信息", notes = "<pre>{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"name\": \"河北省\",\n" +
            "      \"id\": 36,\n" +
            "      \"parentId\": 1\n" +
            "      \"adCode\": 110115\n" +
            "    },\n</pre>" +
            "    <pre>{\n" +
            "      \"name\": \"山西省\",\n" +
            "      \"id\": 230,\n" +
            "      \"parentId\": 1\n" +
            "      \"adCode\": 110115\n" +
            "    },\n</pre>" +
            "    <pre>{\n" +
            "      \"name\": \"内蒙古自治区\",\n" +
            "      \"id\": 372,\n" +
            "      \"parentId\": 1\n" +
            "      \"adCode\": 110115\n" +
            "     }],</pre>" +
            "\"serverTime\": 1572426443079}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级ID(查询省信息父类ID为1)",required = true, dataType = "Long", paramType = "query"),
    })
    public Object findListByParentId(@NotNull(message = "父级ID不能为空") String parentId) {

        log.info("查询城市地址ID为：{}",parentId);
        return nationService.findListByParentCode(parentId);
    }

    @ApiImplicitParam(name = "code", value = "城市代码(如果不传默认查询省)", required = false, paramType = "query", dataType = "String")
    @GetMapping("/findParentListByCode")
    public Object findParentListByCode( String code){
        log.info("查询城市地址Code为：{}",code);
        return nationService.findParentListByCode(code);
    }

}

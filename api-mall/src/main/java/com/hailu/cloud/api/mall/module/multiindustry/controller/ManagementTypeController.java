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

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Validated
@RestController
@Api(tags = "多行业-经营行业")
@RequestMapping("/ManagementType")
public class ManagementTypeController {

    @Autowired
    private ManagementTypeService managementTypeService;


    @ApiOperation(value = "查询经营类型", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': '请求成功',\n" +
            "  'data': {\n" +
            "    'managementId': 5444781580777984,          //编号\n" +
            "    'managementName': '美食'                   //行业名称\n" +
            "    'pictureColour': 'null'                    //图标颜色\n" +
            "    'pictureCode': 'null'                      //图标代码\n" +
            "    'url': 'null'                              //链接地址\n" +
            "  }\n" +
            "}" +
            "</pre>" +
            "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': '请求成功',\n" +
            "  'data': {\n" +
            "    'managementId': 5444781580777986,\n" +
            "    'parentId': 5444781580777984,              //夫Id\n" +
            "    'managementName': '汤/粥'                  //行业名称\n" +
            "    'pictureColour': 'null'                    //图标颜色\n" +
            "    'pictureCode': 'null'                      //图标代码\n" +
            "  }\n" +
            "}" +
            "</pre>")
    @GetMapping("/find/queryBusinessType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父ID（）", paramType = "query", dataType = "long")
    })
    public Object find(@RequestParam(defaultValue = "0") long parentId) {

        Map map = new HashMap(3);
        map.put("parentId",parentId);
        map.put("managementType",1); //经营项目类型  1 生活圈 ； 2  百货
        return managementTypeService.findManagementTypeList(parentId);
    }

}

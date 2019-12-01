package com.hailu.cloud.api.admin.module.mall.controller;

import com.hailu.cloud.api.admin.module.mall.model.ManagementTypeModel;
import com.hailu.cloud.api.admin.module.mall.service.ManagementTypeService;
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
import javax.validation.constraints.NotNull;

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
    @PostMapping("/add/industry")
    public ManagementTypeModel add(@ModelAttribute ManagementTypeModel managementType) throws BusinessException {
        return managementTypeService.insertSelective(managementType);
    }





    @ApiOperation(value = "更改行业标签内容", notes = "<pre>" +
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
    @PostMapping("/upd/industry")
    public ManagementTypeModel upd(@Valid ManagementTypeModel managementType) {
        return managementTypeService.updeteManagementTypeModel(managementType);
    }




    @ApiOperation(value = "查询经营类型列表", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/find/queryBusinessType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父ID（）", paramType = "query", dataType = "long",defaultValue = "0")
    })
    public Object find(@RequestParam long parentId) {
        return managementTypeService.findManagementTypeList(parentId);
    }




    @ApiOperation(value = "查询经营类型详细", notes = "<pre>" +
            "" +
            "</pre>")
    @GetMapping("/find/typeDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "managementI", value = "编号", paramType = "query")
    })
    public Object find(@NotNull(message = "编号不能为空") Long managementI) {
        return managementTypeService.findManagementTypeByManagementId(managementI);
    }



}

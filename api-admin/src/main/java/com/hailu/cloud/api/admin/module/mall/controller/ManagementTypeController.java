package com.hailu.cloud.api.admin.module.mall.controller;

import com.hailu.cloud.api.admin.module.mall.entity.ManagementType;
import com.hailu.cloud.api.admin.module.mall.model.ManagementTypeModel;
import com.hailu.cloud.api.admin.module.mall.service.ManagementTypeService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            @ApiImplicitParam(name = "pageNum", value = "当前页", defaultValue = "1", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "parentId", value = "父ID（）", paramType = "query", dataType = "long",defaultValue = "0"),
            @ApiImplicitParam(name = "mcLevel", value = "层级", paramType = "query", dataType = "int",defaultValue = "1")
    })
    public PageInfoModel<List<ManagementTypeModel>> findList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @Range(min = 10, max = 200, message = "每页显示数量只能在10~200之间")
                            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize, @RequestParam Long parentId,@RequestParam Integer mcLevel) {

        Map map = new HashMap(5);
        map.put("parentId",parentId);
        map.put("mcLevel",mcLevel);
        return managementTypeService.findManagementTypeList(map,pageNum,pageSize);
    }




    @ApiOperation(value = "查询经营类型详细", notes = "<pre>" +
            "" +
            "</pre>")
    @GetMapping("/find/typeDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "managementI", value = "编号", paramType = "query")
    })
    public ManagementTypeModel find(@NotNull(message = "编号不能为空") Long managementI) {
        return managementTypeService.findManagementTypeByManagementId(managementI);
    }



}

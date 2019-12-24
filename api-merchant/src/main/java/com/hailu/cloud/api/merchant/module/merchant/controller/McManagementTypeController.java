package com.hailu.cloud.api.merchant.module.merchant.controller;


import com.hailu.cloud.api.merchant.module.merchant.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.merchant.service.McManagementTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhangmugui
 * @Description: 本地生活圈入驻app接口
 * @Date: 09:32 2019/11/19
 */
@RestController
@RequestMapping("/mcManagementType")
@Validated
@Api(tags = "商户-经营类型")
@Slf4j
public class McManagementTypeController {

    @Autowired
    private McManagementTypeService mcManagementTypeService;

    @ApiOperation(value = "获取经营类型")
    @PostMapping("/businessType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prentId", value = "父类编号id", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "mcType", value = "入驻商户类型,1 生活圈 ，2 百货", paramType = "query", dataType = "int", required = true)
    })
    public List<McManagementType> findGoodsList(Long parentId, Integer mcType) {

        Map<String, Object> map = new HashMap<>(10);
        if (parentId == null) {
            if (mcType == 1) {
                //生活圈过滤百货经营类型
                map.put("isLifeCircle", 1);
            }
            //经营项目类型  1 生活圈百货；2  百货
            map.put("managementType", mcType);
            map.put("parentIdIsNull", 1);
            return mcManagementTypeService.findListByParam(map);
        } else {
            map.clear();
            map.put("parentId", parentId);
            return mcManagementTypeService.findListByParam(map);
        }

    }


}

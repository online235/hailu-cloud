package com.hailu.cloud.api.merchant.module.merchant.controller;


import com.hailu.cloud.api.merchant.module.merchant.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.merchant.parameter.RegisterInformation;
import com.hailu.cloud.api.merchant.module.merchant.service.McManagementTypeService;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.LocalCircleEntryService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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


    @Resource
    private LocalCircleEntryService localCircleEntryService;

    @Autowired
    private McManagementTypeService mcManagementTypeService;

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;


//    @ApiOperation(value = "提交审核(商家注册以及入驻)")
//    @PostMapping("submitAudit")
//    public void register(@ModelAttribute RegisterInformation registerInformation, HttpServletRequest request, BindingResult result) throws Exception {
//
//
//        if (result.hasErrors()) {
//            throw new BusinessException("必填信息不能为空！");
//        }
//        if (registerInformation.getIdCard().length() != 18) {
//            throw new BusinessException("身份证长度不符合");
//        }
//        String val = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + registerInformation.getMoli() + "1");
//        if (!registerInformation.getCode().equals(val)) {
//            throw new BusinessException("无效验证码");
//        }
//        localCircleEntryService.setLocalCircleEntry(registerInformation, 1);
//
//    }


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
                map.put("isLifeCircle", 1);//生活圈过滤百货经营类型
            }
            map.put("managementType", mcType);//经营项目类型  1 生活圈百货；2  百货
            map.put("parentIdIsNull", 1);
            return mcManagementTypeService.findListByParam(map);
        } else {
            map.clear();
            map.put("parentId", parentId);
            return mcManagementTypeService.findListByParam(map);
        }

    }


}

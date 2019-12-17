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
@RequestMapping("/life-circle")
@Validated
@Api(tags = "商户-本地生活圈-APP")
@Slf4j
public class LocalCircleEntryAppController {


    @Resource
    private LocalCircleEntryService localCircleEntryService;

    @Autowired
    private McManagementTypeService mcManagementTypeService;

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;


    @ApiOperation(value = "提交审核(商家注册以及入驻)")
    @PostMapping("submitAudit")
    public void register(@ModelAttribute RegisterInformation registerInformation, HttpServletRequest request, BindingResult result) throws Exception {


        if (result.hasErrors()) {
            throw new BusinessException("必填信息不能为空！");
        }
        if (registerInformation.getIdCard().length() != 18) {
            throw new BusinessException("身份证长度不符合");
        }
        String val = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + registerInformation.getMoli() + "1");
        if (!registerInformation.getCode().equals(val)) {
            throw new BusinessException("无效验证码");
        }
        localCircleEntryService.setLocalCircleEntry(registerInformation, 1);

    }


    @ApiOperation(value = "获取经营类型", notes = "<pre>\n" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': [{\n" +
            "            'managementId': 5444781580777984,            //经营类型id\n" +
            "            'parentId': 0,\n                              //父类id" +
            "            'managementName': '美食'                      // 经营名称\n" +
            "        }, {\n" +
            "            'managementId': 5455544768823811,\n" +
            "            'parentId': 0,\n" +
            "            'managementName': '酒店住宿'\n" +
            "        }, {\n" +
            "            'managementId': 5455544768823812,\n" +
            "            'parentId': 0,\n" +
            "            'managementName': '旅游景点'\n" +
            "        }\n" +
            "    ]\n" +
            "}\n" +
            "</pre>")
    @PostMapping("businessType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prentId", value = "父类编号id", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "mcType", value = "入驻商户类型,1 生活圈 ，2 百货", paramType = "query", dataType = "int",required = true)
    })
    public List<McManagementType> findGoodsList(Long parentId, Integer mcType) {

        Map<String, Object> map = new HashMap<>(10);
        if (parentId == null) {
            if (mcType == 1) {
                map.put("isLifeCircle", 1);//生活圈过滤百货经营类型
            }
            map.put("parentIdIsNull", 1);
            return mcManagementTypeService.findListByParam(map);
        } else {
            map.clear();
            map.put("parentId", parentId);
            return mcManagementTypeService.findListByParam(map);
        }

    }


    @ApiOperation(value = "商户忘记密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginType", value = "登录类型(0:心安&商城; 1:商户, 2:管理员)", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @GetMapping("/rePasswordLogin/{loginType}")
    public void rePasswordLogin(
            @NotBlank(message = "登录类型不能为空")
            @Pattern(regexp = "^[012]$", message = "不支持的登录类型")
            @PathVariable("loginType") String loginType,
            @NotBlank(message = "手机号码不能为空")
            @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确") String phone,
            @NotBlank(message = "验证码不能为空") String code, String password) throws BusinessException {

        localCircleEntryService.merchantRepassword(Integer.valueOf(loginType),password, phone, code);
    }



}

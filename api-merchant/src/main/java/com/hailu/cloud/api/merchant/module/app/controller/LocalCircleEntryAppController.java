package com.hailu.cloud.api.merchant.module.app.controller;


import com.hailu.cloud.api.merchant.module.app.entity.LocalCircleEntry;
import com.hailu.cloud.api.merchant.module.app.parameter.McQualifications;
import com.hailu.cloud.api.merchant.module.app.service.impl.GoodsClassSrevice;
import com.hailu.cloud.api.merchant.module.app.service.impl.LocalCircleEntryService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: Zhangmugui
 * @Description: 本地生活圈入驻app接口
 * @Date: 09:32 2019/11/19
 */
@RestController
@RequestMapping("/app/merchantsettledinApp")
@Validated
@Api(tags = "商户-本地生活圈-APP")
@Slf4j
public class LocalCircleEntryAppController {


    @Resource
    private LocalCircleEntryService localCircleEntryService;

    @Autowired
    private GoodsClassSrevice goodsClassSrevice;

    @Autowired
    private RedisStandAloneClient redis;


//    @ApiOperation(value = "商家注册以及入驻", notes = "<pre>" +
//            "{\n" +
//            "  \"code\": 807,\n" +
//            "  \"msg\": \"用户已存在\",\n" +
//            "  \"data\": null,\n" +
//            "  \"serverTime\": 1571987027320\n" +
//            "}\n\n" +
//            "<pre>" +
//            "{\n" +
//            "  \"code\": 233,\n" +
//            "  \"msg\": \"手机号码已绑定\",\n" +
//            "  \"data\": null,\n" +
//            "  \"serverTime\": 1571987129871\n" +
//            "}\n\n" +
//            "<pre>" +
//            "{\n" +
//            "  \"code\": 207,\n" +
//            "  \"msg\": \"无效的验证码\",\n" +
//            "  \"data\": null,\n" +
//            "  \"serverTime\": 1571987176455\n" +
//            "}\n\n" +
//            "<pre>" +
//            "{\n" +
//            "  \"code\": 204,\n" +
//            "  \"msg\": \"数据状态不正确\",\n" +
//            "  \"data\": null,\n" +
//            "  \"serverTime\": 1571987027320\n" +
//            "}\n\n" +
//            "<pre>" +
//            "{\n" +
//            "  \"code\": 311,\n" +
//            "  \"msg\": \"信息已存在\",\n" +
//            "  \"data\": null,\n" +
//            "  \"serverTime\": 1571987027320\n" +
//            "}\n\n" +
//            "<pre>" +
//            "{\n" +
//            "  \"code\": 201,\n" +
//            "  \"msg\": \"参数错误\",\n" +
//            "  \"data\": null,\n" +
//            "  \"serverTime\": 1571987027320\n" +
//            "}\n\n" +
//            "<pre>" +
//            "{\n" +
//            "  \"code\": 0,\n" +
//            "  \"msg\": \"成功\",\n" +
//            "  \"data\": null,\n" +
//            "  \"serverTime\": 1572339258708\n" +
//            "}")
//    @PostMapping("register")
//    @ResponseBody
//    public Object register(@ModelAttribute RegisterInformation registerInformation, HttpServletRequest request) throws Exception {
//
//
//        boolean exists;
//        exists = xinAbXaSmsService.exists(moli, code);
//        if (!exists) {
//            // 验证码不存在
//            return ResponseUtil.fail(BusinessCode.CODE_NOT_EXISTS.getCode(), BusinessCode.CODE_NOT_EXISTS.getDescription());
//        }
//
//        return mcInfoService.addMcUserAndEntry(mcEntryinFormation, landingaccount, landingpassword, moli, request);
//
//
//    }





    @ApiOperation(value = "提交资质保存", notes = "<pre>\n" +
            "{\n" +
            "    'code': 0,\n" +
            "    'msg': '成功',\n" +
            "    'data': null,\n" +
            "    'serverTime': 1572339258708\n" +
            "}\n" +
            "</pre>")
    @PostMapping("qualificationsSave")
    @ResponseBody
    public void qualificationsSave(@ModelAttribute McQualifications mcQualifications, BindingResult result) throws BusinessException {

        if (result.hasErrors()) {
            throw new BusinessException("信息不能为空");
        }

        LocalCircleEntry localCircleEntry = new LocalCircleEntry();
        BeanUtils.copyProperties(mcQualifications, localCircleEntry);
        localCircleEntryService.updateSelective(localCircleEntry);


    }


    @ApiOperation(value = "提交审核", notes = "<pre>\n" +
            "{\n" +
            "    'code': 0,\n" +
            "    'msg': '成功',\n" +
            "    'data': null,\n" +
            "    'serverTime': 1572339258708\n" +
            "}\n" +
            "</pre>")
    @PostMapping("submitAudit")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "loginType", value = "登录类型", required = true, paramType = "query"),
    })
    public void submitAudit(HttpServletRequest request ,String code, @Pattern(regexp = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$", message = "手机号不正确") String phone, String loginType, BindingResult result) throws BusinessException {

        if (result.hasErrors()) {
            throw new BusinessException("信息不能为空");
        }
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        String val = redis.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + phone + loginType);

        if (!loginInfo.getMemberMobile().equals(phone)) {
            throw new BusinessException("手机号码与注册时不匹配");
        }
        if (!code.equals(val)) {
            throw new BusinessException("验证码不正确");
        }

    }


    @ApiOperation(value = "获取经营类型", notes = "<pre>\n" +
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': [\n" +
            "    {\n" +
            "      'gcId': 237,\n" +
            "      'gcParentId': 0,\n" +
            "      'gcName': '医疗器械'\n" +
            "    },\n" +
            "    {\n" +
            "      'gcId': 238,\n" +
            "      'gcParentId': 0,\n" +
            "      'gcName': '百货购物'\n" +
            "    }\n" +
            "  ],\n" +
            "  'serverTime': 1574234524345\n" +
            "}" +
            "</pre>")
    @PostMapping("businessType")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gcParentId", value = "类型编号", paramType = "query")
    })
    public Object findGoodsList(@NotBlank(message = "编号不能为空") String gcParentId) {

        return goodsClassSrevice.findGoodsClassList(gcParentId);
    }


}

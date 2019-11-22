package com.hailu.cloud.api.merchant.module.lifecircle.controller;


import com.hailu.cloud.api.merchant.module.lifecircle.entity.LocalCircleEntry;
import com.hailu.cloud.api.merchant.module.lifecircle.entity.McUser;
import com.hailu.cloud.api.merchant.module.lifecircle.parameter.RegisterInformation;
import com.hailu.cloud.api.merchant.module.lifecircle.service.McManagementTypeService;
import com.hailu.cloud.api.merchant.module.lifecircle.service.impl.GoodsClassSrevice;
import com.hailu.cloud.api.merchant.module.lifecircle.service.impl.LocalCircleEntryService;
import com.hailu.cloud.api.merchant.module.lifecircle.service.impl.McUserService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
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
import java.util.HashMap;
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
    private RedisStandAloneClient redis;

    @Resource
    private McUserService mcUserService;


    @ApiOperation(value = "提交审核(商家注册以及入驻)", notes = "<pre>" +
            "{\n" +
            "  \"code\": 807,\n" +
            "  \"msg\": \"用户已存在\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987027320\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 233,\n" +
            "  \"msg\": \"手机号码已绑定\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987129871\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 207,\n" +
            "  \"msg\": \"无效的验证码\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987176455\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 204,\n" +
            "  \"msg\": \"数据状态不正确\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987027320\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 311,\n" +
            "  \"msg\": \"信息已存在\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987027320\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 201,\n" +
            "  \"msg\": \"参数错误\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1571987027320\n" +
            "}\n\n" +
            "<pre>" +
            "{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1572339258708\n" +
            "}")
    @PostMapping("submitAudit")
    @ResponseBody
    public void register(@ModelAttribute RegisterInformation registerInformation, HttpServletRequest request, BindingResult result) throws Exception {


        if (result.hasErrors()) {
            throw new BusinessException("必填信息不能为空！");
        }
        LocalCircleEntry localCircleEntry = new LocalCircleEntry();
        BeanUtils.copyProperties(registerInformation, localCircleEntry);
        String val = redis.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + registerInformation.getPhone() + "1");
        if (!registerInformation.getCode().equals(val)) {
            throw new BusinessException("验证码不正确或输入手机号码有误！");
        }
        MerchantUserLoginInfoModel merchantUserLoginInfoModel = mcUserService.insertSelective(registerInformation.getLandingAccount(), registerInformation.getLandingPassword(), registerInformation.getPhone(), registerInformation.getCode());
        String userNumberId = merchantUserLoginInfoModel.getNumberid();
        McUser mcUser = new McUser();
        mcUser.setAccountType(1);
        mcUserService.updateByPrimaryKeySelective(mcUser);
        localCircleEntryService.setLocalCircleEntry(localCircleEntry, userNumberId);

    }



    @ApiOperation(value = "获取经营类型", notes = "<pre>\n" +
            "" +
            "</pre>")
    @PostMapping("businessType")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "prentId", value = "类型编号", paramType = "query")
//    })
    @ResponseBody
    public Object findGoodsList() {

        Map map = new HashMap<>();
        return mcManagementTypeService.findManagementTypeListResult(map);
    }


}

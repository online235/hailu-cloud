package com.hailu.cloud.api.xinan.module.controller;

import com.hailu.cloud.api.xinan.module.entity.Mutualaid;
import com.hailu.cloud.api.xinan.module.service.MutualAidInfoService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安互助接口
 * @Date: 16:32 2019/11/2 0002
 */
@RestController
@RequestMapping("/app/xinanhelp")
@Validated
@Api(tags = "心安-互助")
@Slf4j
public class XinAnMutualAidController {


    @Autowired
    private MutualAidInfoService mutualAidInfoService;



    @ApiOperation(notes = "", value = "上传互助信息")
    @PostMapping("uploadMutualHelp")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="targetAmount", value = "目标金额", required = true, paramType="query", dataType = "BigDecimal", defaultValue = "10000000.00"),
            @ApiImplicitParam(name="title", value = "筹款标题" , required = true, paramType = "query", dataType = "String", defaultValue = "好嗨哟"),
            @ApiImplicitParam(name="explain", value = "救助说明" , required = true, paramType = "query", dataType = "String", defaultValue = "太爽了"),
            @ApiImplicitParam(name="hospitalName", value = "医院名称" , required = true, paramType = "query", dataType = "String", defaultValue = "神经病医院"),
            @ApiImplicitParam(name="hospitalAccount", value = "医院收款账号" , required = true, paramType = "query", dataType = "String", defaultValue = "123456789123456789"),
            @ApiImplicitParam(name="diseaseName", value = "确诊病名" , required = true, paramType = "query", dataType = "String", defaultValue = "艾滋"),

            @ApiImplicitParam(name="picture", value = "图片路径" , required = true, paramType = "query", allowMultiple=true, dataType = "String")

    })
    public void insHelpANDMtualAid(Mutualaid mutualAid, String[] picture, HttpServletRequest request) throws BusinessException {

        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        mutualAid.setMemberId(loginInfo.getUserId());
        mutualAidInfoService.insHelpANDMtualAid(mutualAid,picture);
    }
}

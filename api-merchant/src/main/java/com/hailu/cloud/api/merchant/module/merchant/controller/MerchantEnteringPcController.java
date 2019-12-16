package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.MerchantEnteringService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商户注册
 * @Date: 16:32 2019/11/2 0002
 */
@RestController
@RequestMapping("/merchant")
@Validated
@Api(tags = "商户-入驻")
@Slf4j
public class MerchantEnteringPcController {

    @Autowired
    private MerchantEnteringService merchantEnteringService;
    /**
     * 外部存储的绝对路径
     */
    @Value("${file.store.path:/opt/filestore}")
    private String fileStorePath;

    @ApiOperation(value = "pc添加商家入驻信息", notes = "<pre>" +
            "")
    @PostMapping("entryInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shopname", value = "店铺名称" , required = true, paramType = "query"),
            @ApiImplicitParam(name="realname", value = "真实姓名" , required = true, paramType = "query"),
            @ApiImplicitParam(name="phone", value = "手机号码" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcard", value = "身份证号码" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardtermofvalidity", value = "身份证有效期" ,  paramType = "query"),
            @ApiImplicitParam(name="businessname", value = "执照名称" , required = true, paramType = "query"),
            @ApiImplicitParam(name="nameoflegalperson", value = "法人姓名" , required = true, paramType = "query"),
            @ApiImplicitParam(name="licensedate", value = "执照有效日期" ,  paramType = "query"),
            @ApiImplicitParam(name="longtermlicense", value = "营业执照是否为长期" ,  paramType = "query"),
            @ApiImplicitParam(name="longtermcertificate", value = "身份证是否为长期" ,  paramType = "query"),
            @ApiImplicitParam(name="businesslicensenumber", value = "注册号/新用代码" ,  paramType = "query"),
            @ApiImplicitParam(name="serviceProviderOrNot", value = "是否为服务商" , required = true,  paramType = "query"),
            @ApiImplicitParam(name="remarks", value = "备注" ,  paramType = "query"),

            @ApiImplicitParam(name="licensepositive", value = "营业执照正面" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardimgx", value = "证件照正面" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardimgy", value = "证件照反面" , required = true, paramType = "query")
    })
    public void addMcEntryinFormation(McEntryInformation mcEntryinFormation, HttpServletRequest request) throws BusinessException {
        MerchantUserLoginInfoModel loginInfo = RequestUtils.getMerchantUserLoginInfo();
        if (mcEntryinFormation == null) {
            throw new BusinessException("信息为空");
        }
        mcEntryinFormation.setMcNumberId(loginInfo.getNumberid());
        merchantEnteringService.insertSelective(mcEntryinFormation);
    }

    @ApiOperation(notes = "", value = "pc百货商家入驻信息详情")
    @ResponseBody
    @PostMapping("mcEntryInFormationDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "信息编号", required = true, paramType = "query")
    })
    public Object selectByPrimaryKey(
            @NotBlank(message = "编号不能为空") String numberId){

        return merchantEnteringService.selectByPrimaryKey(numberId);
    }

    @ApiOperation(value = "pc百货更改商家入驻信息")
    @PostMapping("updEntryInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name="shopname", value = "店铺名称" , required = true, paramType = "query"),
            @ApiImplicitParam(name="realname", value = "真实姓名" , required = true, paramType = "query"),
            @ApiImplicitParam(name="phone", value = "手机号码" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcard", value = "身份证号码" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardtermofvalidity", value = "身份证有效期" ,  paramType = "query"),
            @ApiImplicitParam(name="businessname", value = "执照名称" , required = true, paramType = "query"),
            @ApiImplicitParam(name="nameoflegalperson", value = "法人姓名" , required = true, paramType = "query"),
            @ApiImplicitParam(name="licensedate", value = "执照有效日期" ,  paramType = "query"),
            @ApiImplicitParam(name="longtermlicense", value = "营业执照是否为长期" ,  paramType = "query"),
            @ApiImplicitParam(name="longtermcertificate", value = "身份证是否为长期" ,  paramType = "query"),
            @ApiImplicitParam(name="serviceProviderOrNot", value = "是否为服务商" , required = true,  paramType = "query"),
            @ApiImplicitParam(name="remarks", value = "备注" ,  paramType = "query"),

            @ApiImplicitParam(name="licensepositivefile", value = "营业执照正面" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardimgxfile", value = "证件照正面" , required = true, paramType = "query"),
            @ApiImplicitParam(name="idcardimgyfile", value = "证件照反面" , required = true, paramType = "query")
    })
    public void updateMcEntryInformation(McEntryInformation mcEntryinFormation, HttpServletRequest request) throws BusinessException {
        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        if (mcEntryinFormation == null){
            throw new BusinessException("信息为空");
        }
        mcEntryinFormation.setMcNumberId(loginInfo.getUserId());
        merchantEnteringService.updateMcEntryInformation(mcEntryinFormation);

    }


}

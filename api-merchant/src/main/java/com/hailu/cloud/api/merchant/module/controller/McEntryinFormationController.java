package com.hailu.cloud.api.merchant.module.controller;

import com.hailu.cloud.api.merchant.module.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.service.McEntryinFormationService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
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
 * @Description: 商家入驻接口
 * @Date: 16:32 2019/11/2 0002
 */
@RestController
@RequestMapping("/pc/merchantsettledin")
@Validated
@Api(tags = "商户-商家入驻-PC")
@Slf4j
public class McEntryinFormationController {



    @Autowired
    private McEntryinFormationService mcEntryinFormationService;

    /**
     * 外部存储的绝对路径
     */
    @Value("${file.store.path:/opt/filestore}")
    private String fileStorePath;

    @ApiOperation(value = "添加商家入驻信息", notes = "<pre>" +
            "{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": null,\n" +
            "  \"serverTime\": 1572339471892\n" +
            "}")
    @PostMapping("entryInformation")
    @ResponseBody
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
    public Object addMcEntryinFormation(McEntryInformation mcEntryinFormation, HttpServletRequest request) throws BusinessException {
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        if (mcEntryinFormation == null) {
            throw new BusinessException("信息为空");
        }
        mcEntryinFormation.setMcNumberId(loginInfo.getUserId());
        return mcEntryinFormationService.insertSelective(mcEntryinFormation);
    }

    @ApiOperation(value = "显示商家入驻信息" ,notes = "<pre>" +
            "{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": {\n" +
            "    \"total\": 8,                  // 记录条数\n" +
            "    \"pages\": 8,                  // 页数\n" +
            "    \"data\": [\n" +
            "      {\n" +
            "        \"numberid\": \"123456789456465797\",              //编号\n" +
            "        \"mcnumberid\": \"656911572318329926\",            //商家编号\n" +
            "        \"shopname\": \"啊实打实的\",                       //店铺名称\n" +
            "        \"realname\": \"啊我发\",                           //真实姓名\n" +
            "        \"phone\": \"13927555292\",                         //手机号码\n" +
            "        \"idcard\": \"123456789123456788\",                //身份证号码\n" +
            "        \"idcardimgx\": null,                              //身份证正面\n" +
            "        \"idcardimgy\": null,                              //身份证反面\n" +
            "        \"idcardtermofvalidity\": \"2019-10-23\",          //身份证有效期\n" +
            "        \"longtermcertificate\": false,                    //身份证是否为长期\n" +
            "        \"businessname\": \"阿斯达\",                      //执照名称\n" +
            "        \"nameoflegalperson\": \"发顺丰\",                 //法人姓名\n" +
            "        \"licensedate\": \"2019-10-25\",                   //执照有效日期\n" +
            "        \"longtermlicense\": false,                        //营业执照是否为长期\n" +
            "        \"licensepositive\": null,                         //营业执照正面照\n" +
            "        \"toexamine\": \"审核不通过\",                      //审核\n" +
            "        \"createdat\": 1572318329926,                      //创建时间\n" +
            "        \"updatedat\": 1572318329926,                      //跟新时间\n" +
            "        \"businesslicensenumber\": null                    //营业执照注册号\n" +
            "      }\n" +
            "    ],\n" +
            "    \"limit\": 1,                  //每页数量\n" +
            "    \"page\": 1                    // 当前页号，外界传入\n" +
            "  },\n" +
            "  \"serverTime\": 1572333198217\n" +
            "}")
    @ResponseBody
    @PostMapping("entryInformationList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopname", value = "店铺名称",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "每页数量", required = true, paramType = "query")
    })
    public Object selectMcEntryinFormationList(String shopname, String phone ,
                                               @RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer limit){

        return mcEntryinFormationService.selectMcEntryinFormationList(shopname,phone,page,limit);
    }

    @ApiOperation(notes = "", value = "商家入驻信息详情")
    @ResponseBody
    @PostMapping("mcEntryInFormationDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "信息编号", required = true, paramType = "query")
    })
    public Object selectByPrimaryKey(
            @NotBlank(message = "编号不能为空") String numberId){

        return mcEntryinFormationService.selectByPrimaryKey(numberId);
    }

    @ApiOperation(notes = "", value = "更改入驻审核状态")
    @ResponseBody
    @PostMapping("changeState")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "信息编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "toExamine", value = "状态", required = true, paramType = "query")
    })
    public void updateToExamineByNumberId(
            @NotBlank(message = "编号不能为空") String numberId,
            @NotBlank(message = "更改的状态不能为空") String toExamine){

        mcEntryinFormationService.updateToExamineByNumberId(numberId,toExamine);
    }

    @ApiOperation(value = "更改商家入驻信息")
    @PostMapping("updEntryInformation")
    @ResponseBody
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
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        if (mcEntryinFormation == null){
            throw new BusinessException("信息为空");
        }
        mcEntryinFormation.setMcNumberId(loginInfo.getUserId());
        mcEntryinFormationService.updateMcEntryInformation(mcEntryinFormation);
    }


    @ApiOperation(value = "删除商家入驻信息")
    @PostMapping("delEntryInformation")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="numberId", value = "编号" , required = true, paramType = "query")
    })
    public void deleteByPrimaryKey(
            @NotBlank(message = "编号不能为空") String numberId){

        mcEntryinFormationService.deleteByPrimaryKey(numberId);
    }

































}

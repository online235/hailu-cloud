package com.hailu.cloud.api.admin.module.merchant.controller;


import com.hailu.cloud.api.admin.module.merchant.entity.LocalCircleEntry;
import com.hailu.cloud.api.admin.module.merchant.parmeter.LocalCircleListParameter;
import com.hailu.cloud.api.admin.module.merchant.parmeter.UpdateLocalCircleEntryParameter;
import com.hailu.cloud.api.admin.module.merchant.service.LocalCircleEntryAdminService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: zhangmugui
 * @Description: 生活圈后台
 */
@RestController
@RequestMapping("/pc/life-circle")
@Validated
@Api(tags = "商户-商家入驻-生活圈后台")
@Slf4j
public class McLocalCircleEntryController {


    @Resource
    private LocalCircleEntryAdminService localCircleEntryAdminService;


    @ApiOperation(value = "显示生活圈入驻列表", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'totalPage': 1,\n" +
            "        'total': 9,\n" +
            "        'datas': [{\n" +
            "                'numberId': '5819938384165404',                        //编号\n" +
            "                'mcNumberId': '5819938384165402',                      //商家编号\n" +
            "                'shopName': '虾米',                                      //店铺名称\n" +
            "                'realName':null,                                       //真实姓名\n" +
            "                'phone': '13125676567',                                //店铺手机号码\n" +
            "                'idCard': '122222222222222222',                        //身份证号码\n" +
            "                'idcardImgx': '/life-circle/2019-11-26/1ebb512f568c467493ac3ae80aa60508-1574761045641.jpg',        //身份证正面\n" +
            "                'idcardImgy': '/life-circle/2019-11-26/f9fd33b0289046bc821e88e3e7fed683-1574761066029.jpg',        //身份证反面\n" +
            "                'idcardtermofValidity':null,                               //身份证有效期\n" +
            "                'longTermCertificate':false,                                //身份证是否为长期\n" +
            "                'firstManagementTypeId': 5444781580777984,                 //一级经营类型表id\n" +
            "                'secondManagementTypeId': 5444781580777986,                //二级经营类型表id\n" +
            "                'businessLicenseNumber': '5435345',                        //营业执照注册号\n" +
            "                'businessName':null,                                       //执照名称\n" +
            "                'nameOfLegalPerson': '小明',                               //法人姓名\n" +
            "                'licenseDate':null,                                       //执照有效日期\n" +
            "               'longTermLicense':false,                                   //营业执照是否为长期\n" +
            "                'licensePositive': '/life-circle/2019-11-26/3ac843bb592d4f1a82c90d40e75ff378-1574761069353.jpg',//营业执照正面照\n" +
            "                'thirdPartyLinks':'',                                     //第三方链接\n" +
            "               'invitationCode':'',                                       //入驻邀请码\n" +
            "               'serviceProviderOrNot':'',                                 //是否为服务商\n" +
            "               'toExamine': 1,                                            //审核\n" +
            "                'toExamineDisPlay': '审核中',\n" +
            "                'cityCode': '110100',                                    //市code\n" +
            "                'provinceCode': '110000',                                //省code\n" +
            "               'remarks':'',                                             //备注\n" +
            "                'areaCode': '110101',                                    //区code\n" +
            "                'detailAddress': '九号',                                  //详细地址\n" +
            "                'createdat': 1574761147694,                              //创建时间戳\n" +
            "                'updatedat': 1574761147694,                              //更新时间戳\n" +
            "                'enclosures': ''                                         //附件信息\n" +
            "            }, ]\n" +
            "    }\n" +
            "}" +
            "</pre>")
    @PostMapping("localCircleEntryList")
    public PageInfoModel<List<LocalCircleEntry>> selectMcEntryinFormationList(@ModelAttribute LocalCircleListParameter localCircleListParameter, HttpServletRequest request) {

        return localCircleEntryAdminService.selectLocalCircleEntryList(localCircleListParameter);

    }


    @ApiOperation(value = "显示生活圈入驻信息详情",notes = "<pre>"+
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "                'numberId': '5819938384165404',                        //编号\n" +
            "                'mcNumberId': '5819938384165402',                      //商家编号\n" +
            "                'shopName': '虾米',                                      //店铺名称\n" +
            "                'realName':null,                                       //真实姓名\n" +
            "                'phone': '13125676567',                                //店铺手机号码\n" +
            "                'idCard': '122222222222222222',                        //身份证号码\n" +
            "                'idcardImgx': '/life-circle/2019-11-26/1ebb512f568c467493ac3ae80aa60508-1574761045641.jpg',        //身份证正面\n" +
            "                'idcardImgy': '/life-circle/2019-11-26/f9fd33b0289046bc821e88e3e7fed683-1574761066029.jpg',        //身份证反面\n" +
            "                'idcardtermofValidity':null,                               //身份证有效期\n" +
            "                'longTermCertificate':false,                                //身份证是否为长期\n" +
            "                'firstManagementTypeId': 5444781580777984,                 //一级经营类型表id\n" +
            "                'secondManagementTypeId': 5444781580777986,                //二级经营类型表id\n" +
            "                'businessLicenseNumber': '5435345',                        //营业执照注册号\n" +
            "                'businessName':null,                                       //执照名称\n" +
            "                'nameOfLegalPerson': '小明',                               //法人姓名\n" +
            "                'licenseDate':null,                                       //执照有效日期\n" +
            "               'longTermLicense':false,                                   //营业执照是否为长期\n" +
            "                'licensePositive': '/life-circle/2019-11-26/3ac843bb592d4f1a82c90d40e75ff378-1574761069353.jpg',//营业执照正面照\n" +
            "                'thirdPartyLinks':'',                                     //第三方链接\n" +
            "               'invitationCode':'',                                       //入驻邀请码\n" +
            "               'serviceProviderOrNot':'',                                 //是否为服务商\n" +
            "               'toExamine': 1,                                            //审核\n" +
            "                'toExamineDisPlay': '审核中',\n" +
            "                'cityCode': '110100',                                    //市code\n" +
            "                'provinceCode': '110000',                                //省code\n" +
            "               'remarks':'',                                             //备注\n" +
            "                'areaCode': '110101',                                    //区code\n" +
            "                'detailAddress': '九号',                                  //详细地址\n" +
            "                'createdat': 1574761147694,                              //创建时间戳\n" +
            "                'updatedat': 1574761147694,                              //更新时间戳\n" +
            "                'enclosures': ''                                         //附件信息\n" +
            "            }" +
            "}\n"
            +"<pre>")
    @PostMapping("localCircleEntryDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "编号ID", required = true, paramType = "query")
    })
    public LocalCircleEntry selectByPrimaryKey(
            @NotBlank(message = "编号不能为空") String numberId) {

        return localCircleEntryAdminService.selectByPrimaryKey(numberId);
    }


    @ApiOperation(value = "更改生活圈入驻审核状态",notes="<pre>"+
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功'\n" +
            "}"+
            "<pre>")
    @PostMapping("changeLocalCircleState")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "信息编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "toExamine", value = "状态", required = true, paramType = "query")
    })
    public void updateToExamineByNumberId(
            @NotBlank(message = "编号不能为空") String numberId,
            @NotNull(message = "更改的状态不能为空") Integer toExamine) {

        localCircleEntryAdminService.updateLocalCircleEntryStatus(numberId, toExamine);


    }


    @ApiOperation(value = "更改生活圈商家入驻信息",notes = "<pre>"+
            "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\"\n" +
            "}"
            +"pre")
    @PostMapping("updLocalCircleEntry")
    public void updateMcEntryInformation(@ModelAttribute UpdateLocalCircleEntryParameter updateLocalCircleEntryParameter, BindingResult result, HttpServletRequest request) throws BusinessException {

        if (result.hasErrors()) {
            throw new BusinessException("必传参数不能为空！");
        }
        LocalCircleEntry localCircleEntry = new LocalCircleEntry();
        BeanUtils.copyProperties(updateLocalCircleEntryParameter, localCircleEntry);
        localCircleEntry.setUpdatedat(System.currentTimeMillis());
        localCircleEntryAdminService.updateLocalCircleEntry(localCircleEntry);

    }


    @ApiOperation(value = "删除生活圈商家入驻信息",notes = "<pre>"+
            "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\"\n" +
            "}"
            +"pre")
    @PostMapping("delLocalCircleEntry")
    @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query")
    public void deleteByPrimaryKey(@NotBlank(message = "编号不能为空") String numberId) {

        localCircleEntryAdminService.deleteByPrimaryKey(numberId);
    }


}

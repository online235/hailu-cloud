package com.hailu.cloud.api.admin.module.merchant.controller;


import com.hailu.cloud.api.admin.module.merchant.entity.LocalCircleEntry;
import com.hailu.cloud.api.admin.module.merchant.parmeter.LocalCircleListParameter;
import com.hailu.cloud.api.admin.module.merchant.parmeter.UpdateLocalCircleEntryParameter;
import com.hailu.cloud.api.admin.module.merchant.service.LocalCircleEntryAdminService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
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
            "                'numberId': '5819938384165404',\n" +
            "                'mcNumberId': '5819938384165402',\n" +
            "                'shopName': '虾米',\n" +
            "                'phone': '13125676567',\n" +
            "                'idCard': '122222222222222222',\n" +
            "                'idcardImgx': '/life-circle/2019-11-26/1ebb512f568c467493ac3ae80aa60508-1574761045641.jpg',\n" +
            "                'idcardImgy': '/life-circle/2019-11-26/f9fd33b0289046bc821e88e3e7fed683-1574761066029.jpg',\n" +
            "                'firstManagementTypeId': 5444781580777984,\n" +
            "                'secondManagementTypeId': 5444781580777986,\n" +
            "                'businessLicenseNumber': '5435345',\n" +
            "                'nameOfLegalPerson': '小明',\n" +
            "                'licensePositive': '/life-circle/2019-11-26/3ac843bb592d4f1a82c90d40e75ff378-1574761069353.jpg',\n" +
            "                'toExamine': 1,\n" +
            "                'toExamineDisPlay': '审核中',\n" +
            "                'cityCode': '110100',\n" +
            "                'provinceCode': '110000',\n" +
            "                'areaCode': '110101',\n" +
            "                'detailAddress': '九号',\n" +
            "                'createdat': 1574761147694,\n" +
            "                'updatedat': 1574761147694,\n" +
            "                'enclosures': ''\n" +
            "            }, {\n" +
            "                'numberId': '592028905',\n" +
            "                'mcNumberId': '1031302017',\n" +
            "                'shopName': '好喝的奶茶',\n" +
            "                'phone': '1312863894',\n" +
            "                'idCard': '123212343232454324',\n" +
            "                'idcardImgx': '/String',\n" +
            "                'idcardImgy': '/String',\n" +
            "                'firstManagementTypeId': 3,\n" +
            "                'businessLicenseNumber': '53446457567573524123232324232',\n" +
            "                'nameOfLegalPerson': '/String',\n" +
            "                'licensePositive': '/String',\n" +
            "                'thirdPartyLinks': '/String',\n" +
            "                'toExamine': 1,\n" +
            "                'toExamineDisPlay': '审核中',\n" +
            "                'cityCode': '37',\n" +
            "                'provinceCode': '36',\n" +
            "                'areaCode': '38',\n" +
            "                'detailAddress': '龙街巷43号',\n" +
            "                'createdat': 1574221332240,\n" +
            "                'updatedat': 1574221332240\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @PostMapping("localCircleEntryList")
    public Object selectMcEntryinFormationList(@ModelAttribute LocalCircleListParameter localCircleListParameter, HttpServletRequest request) {

        return localCircleEntryAdminService.selectLocalCircleEntryList(localCircleListParameter);

    }


    @ApiOperation(value = "显示生活圈入驻信息详情",notes = "<pre>"+
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'numberId': '5819938384165393',\n" +
            "        'mcNumberId': '5819938384165392',\n" +
            "        'shopName': '小明店铺',\n" +
            "        'phone': '13128643784',\n" +
            "        'idCard': '124412354354356456',\n" +
            "        'idcardImgx': 'undefined',\n" +
            "        'idcardImgy': 'undefined',\n" +
            "        'firstManagementTypeId': 5444781580777984,\n" +
            "        'secondManagementTypeId': 5444781580777986,\n" +
            "        'businessLicenseNumber': 'S562432',\n" +
            "        'nameOfLegalPerson': '小明',\n" +
            "        'licensePositive': 'undefined',\n" +
            "        'toExamine': 1,\n" +
            "        'toExamineDisPlay': '审核中',\n" +
            "        'detailAddress': '北街八号',\n" +
            "        'createdat': 1574758182043,\n" +
            "        'updatedat': 1574758182043,\n" +
            "        'enclosures': ''\n" +
            "    }\n" +
            "}\n"
            +"<pre>")
    @PostMapping("localCircleEntryDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "编号ID", required = true, paramType = "query")
    })
    public Object selectByPrimaryKey(
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
    public void updateMcEntryInformation(UpdateLocalCircleEntryParameter updateLocalCircleEntryParameter, BindingResult result, HttpServletRequest request) throws BusinessException {

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

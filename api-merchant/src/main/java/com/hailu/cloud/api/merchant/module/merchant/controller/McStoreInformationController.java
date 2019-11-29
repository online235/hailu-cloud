package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.lifecircle.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.lifecircle.service.McManagementTypeService;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.parameter.McStoreInformationModel;
import com.hailu.cloud.api.merchant.module.merchant.result.McStoreInformationResult;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McStoreInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/store")
@Validated
@Api(tags = "商户-物联云商-APP")
@Slf4j
public class McStoreInformationController {

    @Autowired
    private McStoreInformationService mcStoreInformationService;


    @Resource
    private McManagementTypeService mcManagementTypeService;



    @ApiOperation(value = "更改信息店铺信息",notes = "<prep>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功'\n" +
            "}\n" +
            "</prep>")
    @PostMapping("/changeInformation")
    @ApiImplicitParam(name = "wee", value = "每周营业日用（1星期日，2星期一）", allowMultiple = true, paramType = "query", dataType = "int")
    public void updateBYMcEntryInformation(@ModelAttribute McStoreInformationModel mcStoreInformationModel, int[] wee) {
        mcStoreInformationService.updateBYMcEntryInformation(mcStoreInformationModel, wee);
    }



    @ApiOperation(value = "根据店铺id查看店铺信息",notes = "<pre>"+
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',                   \n" +
            "    'data': {\n" +
            "        'areaCode': 'string',                  //地区code\n" +
            "        'businessState': 0,                    //营业状态(1-营业中，2-休息中)\n" +
            "        'businessStateDisplay': 0,              //营业状态\n" +
            "        'cityCode': 'string',\n" +
            "        'closingTime': '2019-11-29T03:07:58.480Z',   //关闭时间\n" +
            "        'createdat': '2019-11-29T03:07:58.480Z',     //创建时间\n" +
            "        'detailAddress': 'string',             //详细地址\n" +
            "        'id': 0,                               //店铺id\n" +
            "        'mcNumberId': 'string',                //商户id\n" +
            "        'openingTime': '2019-11-29T03:07:58.480Z',   //开店时间\n" +
            "        'perCapitaPrice': 0,                   //人均价格\n" +
            "        'phone': 'string',                     //店铺联系电话\n" +
            "        'provinceCode': 'string',              //省id\t\t\n" +
            "        'shopName': 'string',                  //店铺名字\n" +
            "        'storeDetails': 'string',              //店铺详情\n" +
            "        'storeSonType': 0,                     //店铺子类型ID\n" +
            "        'storeTotalType': 0,                   //店铺总类型ID\n" +
            "        'toExamine': 0,                        //审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')'\n" +
            "        'toExamineDisplay': 0,                 //审核状态\n" +
            "        'updatedat': '2019-11-29T03:07:58.480Z',    //更新时间\n" +
            "        'weekDay': 'string',                   //每周营业日用，“；”隔开（例1；2；3；4:）\n" +
            "        'weekDayDisplay': 'string'             //周一，周二\n" +
            "    }\n" +
            "}"
            +"</pre>")
    @PostMapping("storeInformationById")
    @ApiImplicitParam(name = "id", value = "店铺id",  paramType = "query", dataType = "Long",required = true)
    public McStoreInformation findMcStoreInformation(@RequestParam(value = "id") Long id) {

        return mcStoreInformationService.findMcStoreInformationById(id);

    }



    @ApiOperation(value = "商户查看店铺信息",notes = "<pre>"+
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',                   \n" +
            "    'data': {\n" +
            "        'areaCode': 'string',                  //地区code\n" +
            "        'businessStateDisplay': 0,              //营业状态\n" +
            "        'cityCode': 'string',\n" +
            "        'closingTime': '2019-11-29T03:07:58.480Z',   //关闭时间\n" +
            "        'createdat': '2019-11-29T03:07:58.480Z',     //创建时间\n" +
            "        'detailAddress': 'string',             //详细地址\n" +
            "        'id': 0,                               //店铺id\n" +
            "        'mcNumberId': 'string',                //商户id\n" +
            "        'openingTime': '2019-11-29T03:07:58.480Z',   //开店时间\n" +
            "        'perCapitaPrice': 0,                   //人均价格\n" +
            "        'phone': 'string',                     //店铺联系电话\n" +
            "        'provinceCode': 'string',              //省id\t\t\n" +
            "        'shopName': 'string',                  //店铺名字\n" +
            "        'storeDetails': 'string',              //店铺详情\n" +
            "        'storeSonTypeDisPlay': 0,                     //店铺子类型\n" +
            "        'storeTotalTypeDisPlay': 0,                   //店铺总类型\n" +
            "        'toExamineDisplay': 0,                 //审核状态\n" +
            "        'updatedat': '2019-11-29T03:07:58.480Z',    //更新时间\n" +
            "        'weekDay': 'string',                   //每周营业日用，“；”隔开（例1,2,3,4:）\n" +
            "        'weekDayDisplay': 'string'             //周一，周二\n" +
            "    }\n" +
            "}"
            +"</pre>")
    @PostMapping("storeInformation")
    public McStoreInformationResult findMcStoreInformation() {

        McStoreInformationResult mcStoreInformationResult = new McStoreInformationResult();
        McStoreInformation mcStoreInformation = mcStoreInformationService.findMcStoreInformation();
        BeanUtils.copyProperties(mcStoreInformation,mcStoreInformationResult);
        if(mcStoreInformation.getStoreTotalType() != null && mcStoreInformation.getStoreTotalType() != 0){
            McManagementType mcManagementType = mcManagementTypeService.findManagementById(mcStoreInformation.getStoreTotalType());
            mcStoreInformationResult.setStoreTotalTypeDisPlay(mcManagementType.getManagementName());

        }
        if(mcStoreInformation.getStoreSonType() != null && mcStoreInformation.getStoreSonType() != 0){
            McManagementType mcManagementType1 = mcManagementTypeService.findManagementById(mcStoreInformation.getStoreSonType());
            mcStoreInformationResult.setStoreSonTypeDisPlay(mcManagementType1.getManagementName());
        }
        return mcStoreInformationResult;
    }



}

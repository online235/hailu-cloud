package com.hailu.cloud.api.xinan.module.controller;

import com.hailu.cloud.api.xinan.module.service.XinAnRescueInfoService;
import com.hailu.cloud.api.xinan.module.service.XinAnRescueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@Validated
@RequestMapping("/pc/xinAnBackstage")
@Api(tags = "心安-救助-管理后台")
public class RescueContorller {

    @Autowired
    private XinAnRescueInfoService xinAnRescueInfoService;

    @Autowired
    private XinAnRescueService xinAnRescueService;


    @ApiOperation(value = "救助详情", notes = "<pre>"+
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': [\n" +
            "    {\n" +
            "      'instructions': '太爽了',                                     //救助详细说明\n" +
            "      'targetAmount': 10000000,                                    //目标金额\n" +
            "      'numberId': '1362898073',                                    //救助编号\n" +
            "      'cityId': null,                                              //城市Id\n" +
            "      'title': '好嗨哟',                                            //救助标题\n" +
            "      'provinceId': null,                                          //省份Id\n" +
            "      'rescueType': null,                                          //救助类型\n" +
            "      'helpTimes': null,                                           //帮助次数\n" +
            "      'examine': 审核中,                                             //审核\n" +
            "      'createdat': '2019-11-14 16:33:37',                          //创建时间\n" +
            "      'cash': null,                                                //现金额\n" +
            "      'imageList': [                                               //图片数组\n" +
            "        '/aadd/sdsd/rrr.jpg',\n" +
            "        '/aadd/sdsd/qqq.jpg',\n" +
            "        '/aadd/sdsd/www.jpg',\n" +
            "        '/aadd/sdsd/aaa.jpg'\n" +
            "      ],\n" +
            "      'memberId': '5075b82803bc4065975fbb545a89a91a',              //发起用户编号\n" +
            "      'updatedat': '2019-11-14 16:33:37'                           //更新时间\n" +
            "    }\n" +
            "  ],\n" +
            "  'serverTime': 1573799041109\n" +
            "}</pre>")
    @PostMapping("/rescueDetails")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "救助编号", required = true, paramType = "query",dataType = "String")
    })
    public Object findRescue(String numberId){
        return xinAnRescueInfoService.findRescue(numberId);
    }

    @ApiOperation(notes = "<pre>" +
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': {\n" +
            "    'navigatePages': 8,\n" +
            "    'total': 4,                            //总记录数\n" +
            "    'data': [\n" +
            "      {\n" +
            "        'instructions': '太爽了',                                              //说明\n" +
            "        'targetAmount': 10000000,                                              //目标金额\n" +
            "        'examine': '审核中',                                                   //审核状态\n" +
            "        'numberId': '1362898073',                                              //救助编号\n" +
            "        'cityId': null,                                                        //城市Id\n" +
            "        'title': '好嗨哟',                                                     //标题\n" +
            "        'provinceId': null,                                                    //省份Id\n" +
            "        'rescueType': null,                                                    //救助类型\n" +
            "        'helpTimes': null,                                                     //救助次数\n" +
            "        'createdat': '2019-11-14 16:33:37',                                    //创建时间\n" +
            "        'cash': null,                                                          //现金额\n" +
            "        'memberId': '5075b82803bc4065975fbb545a89a91a',                        //会员编号\n" +
            "        'updatedat': '2019-11-14 16:33:37'                                     //更是时间\n" +
            "      }\n" +
            "    ],\n" +
            "    'pageNum': 1,                      //当前页 （外界传入）\n" +
            "    'pageSize': 1,                     //页数据条数（外界传入）\n" +
            "    'size': 1,                         //当前页数据条数\n" +
            "    'pages': 4,                        //总页数\n" +
            "    'prePage': 0,                      //前一页\n" +
            "    'nextPage': 2,                     //第一页\n" +
            "    'navigatePages': 8,                //导航页码数\n" +
            "    'navigatepageNums': [              //所有导航页号\n" +
            "  },\n" +
            "  'serverTime': 1573802941537\n" +
            "}", value = "救助审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示数量", defaultValue = "20", required = true, paramType = "query", dataType = "int")
    })
    @PostMapping("/rescueList")
    @ResponseBody
    public Object findXaRescueList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer size){

        return xinAnRescueService.findXaRescueListAll(page,size);
    }

    @ApiOperation(value = "更改救助审核状态", notes = "<pre>" +
            "{\n" +
            "  \"errno\": 0,\n" +
            "  \"errmsg\": \"成功\"\n" +
            "}</pre>")
    @PostMapping("/changeState")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "救助编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "examine", value = "审核(2-审核通过、3-审核不通过)", required = true, paramType = "query", dataType = "String")
    })
    public void updateExamineByNumberId(
            @NotBlank(message = "编号不能为空") String numberId,
            @NotBlank(message = "审核状态不能为空") String examine){

        xinAnRescueService.updateByPrimaryKeySelective(numberId,examine);
    }

}

package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.entity.Rescue;
import com.hailu.cloud.api.xinan.module.app.service.impl.RescueInfoService;
import com.hailu.cloud.api.xinan.module.app.service.impl.RescueService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;

@RestController
@RequestMapping("/app/xaRescue")
@Validated
@Api(tags = "心安-救助")
@Slf4j
public class RescueController {

    @Autowired
    private RescueInfoService rescueInfoService;

    @Autowired
    private RescueService rescueService;


    @ApiOperation(notes = "", value = "上传救助信息")
    @PostMapping("/uploadRescueHelp")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="targetAmount", value = "目标金额", required = true, paramType="query", dataType = "BigDecimal", defaultValue = "10000000.00"),
            @ApiImplicitParam(name="title", value = "筹款标题" , required = true, paramType = "query", dataType = "String", defaultValue = "好嗨哟"),
            @ApiImplicitParam(name="instructions", value = "救助说明" , required = true, paramType = "query", dataType = "String", defaultValue = "太爽了"),

            @ApiImplicitParam(name="picture", value = "图片路径" , required = true, paramType = "query", allowMultiple=true, dataType = "String")

    })
    public void insHelpANDMtualAid(Rescue rescue, String[] picture) throws BusinessException {
        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        rescue.setMemberId(loginInfo.getUserId());
        rescueInfoService.insRescueAndRictures(rescue,picture);
    }

    @ApiOperation(notes = "<pre>" +
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': {\n" +
            "    'total': 4,                            //总记录数\n" +
            "    'list': [\n" +
            "      {\n" +
            "        'helpTimes': null,                                               //救助次数\n" +
            "        'numberId': '1362898073',                                       //救助编号\n" +
            "        'title': '好嗨哟',                                              //标题\n" +
            "        'picture': '/aadd/sdsd/rrr.jpg',                               //封面图片\n" +
            "        'rescueType': null,                                            //救助类型\n" +
            "        'memberId': '5075b82803bc4065975fbb545a89a91a'                 //会员编号\n" +
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
            "      1,\n" +
            "      2,\n" +
            "      3,\n" +
            "      4\n" +
            "    ],\n" +
            "  },\n" +
            "  'serverTime': 1572921370799\n" +
            "}", value = "救助列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示数量", defaultValue = "20", required = true, paramType = "query", dataType = "int")
    })
    @PostMapping("/rescueList")
    @ResponseBody
    public Object findXaRescueList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false)
                    Integer size) throws BusinessException {
        return rescueService.findXaRescueList(page,size);
    }

    @ApiOperation(notes = "<pre>"+
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
            "}</pre>", value = "救助详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/rescueDetails")
    @ResponseBody
    public Object findXaRescue(String numberId){
        return rescueInfoService.findRescue(numberId);
    }
}

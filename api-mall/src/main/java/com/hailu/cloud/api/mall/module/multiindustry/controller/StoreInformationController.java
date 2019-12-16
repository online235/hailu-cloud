package com.hailu.cloud.api.mall.module.multiindustry.controller;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McShopTag;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationListResult;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationResultModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.McShopTagService;
import com.hailu.cloud.api.mall.module.multiindustry.service.McStoreAlbumMallService;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/multiIndustry")
@Validated
@Api(tags = "商城-多行业-APP-店铺")
@Slf4j
public class StoreInformationController {


    @Autowired
    private StoreInformationService storeInformationService;

    @Resource
    private McShopTagService mcShopTagService;


    @ApiOperation(value = "店铺查询-分页", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': '请求成功',\n" +
            "  'data': {\n" +
            "    'totalPage': 1,                    //总页数\n" +
            "    'total': 1,                        //总记录数\n" +
            "    'datas': [\n" +
            "      {\n" +
            "        'id': 6157634482787330,                    //店铺编号\n" +
            "        'mcNumberId': '6157634482787328',          //商家编号\n" +
            "        'shopName': '经营测试',                    //店铺名称\n" +
            "        'phone': '13129876857',                    //店铺联系电话\n" +
            "        'provinceCode': '110000',                  //省Id\n" +
            "        'cityCode': '110100',                      //市Id\n" +
            "        'areaCode': '110101',                      //区id\n" +
            "        'detailAddress': '八号解放道',              //店铺详细地址\n" +
            "        'storeTotalType': 5444781580777984,        //店铺总类型ID\n" +
            "        'storeSonType': 5444781580777984,          //店铺子类型ID\n" +
            "        'businessState': 1,                        //营业状态(1-营业中，2-休息中)\n" +
            "        'closingTime': '18:00',                    //关闭时间\n" +
            "        'openingTime': '9:00',                     //开发时间\n" +
            "        'createdat': '2019-11-27 14:30:23',        //创建时间\n" +
            "        'updatedat': '2019-11-27 14:30:23',        //更新时间\n" +
            "        'defaultHead': 'null',                     //店铺头像\n" +
            "        'toExamine': 2,\n" +
            "        'weekDay': '1,2,4,6'                       //每周营业（例1，2，3，4）\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeTotalType", value = "经营父类型ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "storeSonType", value = "经营子类型ID", paramType = "query"),
            @ApiImplicitParam(name = "cityCode", value = "市code", paramType = "query"),
            @ApiImplicitParam(name = "areaCode", value = "区code", paramType = "query"),
            @ApiImplicitParam(name = "tagId", value = "标签编号", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", paramType = "query")
    })
    @GetMapping("/shopEnquiry")
    public PageInfoModel<List<StoreInformationListResult>> findStoreInformationList(
            @NotNull(message = "经营父类型ID不能为空") Long storeTotalType,
            Long storeSonType,
            String cityCode,
            String areaCode,
            Integer tagId,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false)
                    Integer size) throws ParseException {

        return storeInformationService.findStoreInformationList(storeTotalType, storeSonType, cityCode, areaCode, tagId, size, page);
    }

    @ApiOperation(value = "根据编号查询店铺详细信息" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺编号", required = true, paramType = "query")
    })
    @GetMapping("/shopDetails")
    public StoreInformationResultModel findStoreInformation(
            @NotNull(message = "编号不能为空") Long id) throws BusinessException {


        return storeInformationService.findStoreInformationLeftAlbum(id);
    }

    @ApiOperation(value = "根据店铺编号查询店铺下的标签" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺编号", required = true, paramType = "query")
    })
    @GetMapping("/shopLabel")
    public List<McShopTag> findMcShopTagListByStoreId(
            @NotNull(message = "编号不能为空") Long storeId) {


        return mcShopTagService.findMcShopTagListByStoreId(storeId);
    }


//    @ApiOperation(value = "获取店铺轮播图接口")
//    @ApiImplicitParam(name = "storeId", value = "店铺编号", required = true, paramType = "query")
//    @GetMapping("/getShopAlbum")
//    public List<McStoreAlbum> getShopAlbum(
//            @NotNull(message = "编号不能为空") Long storeId) throws BusinessException {
//
//        Map<String, Object> map = new HashMap<>(4);
//        map.put("storeId", storeId);
//        return mcStoreAlbumMallService.findListByParam(map);
//
//    }



}

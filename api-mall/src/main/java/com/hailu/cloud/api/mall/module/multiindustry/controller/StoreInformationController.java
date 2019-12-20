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

    @Autowired
    private McStoreAlbumMallService mcStoreAlbumMallService;


    @ApiOperation(value = "店铺查询-分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeTotalType", value = "经营父类型ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "storeSonType", value = "经营子类型ID", paramType = "query"),
            @ApiImplicitParam(name = "cityCode", value = "市code", paramType = "query"),
            @ApiImplicitParam(name = "areaCode", value = "区code", paramType = "query"),
            @ApiImplicitParam(name = "shopName", value = "店铺名称", paramType = "query"),
            @ApiImplicitParam(name = "priceRanking", value = "人均价格排序(1-低到高、2-高到低、智能排序不传或者-0)", paramType = "query"),
            @ApiImplicitParam(name = "startingPrice", value = "开始价格", paramType = "query"),
            @ApiImplicitParam(name = "closingPrice", value = "结束价格", paramType = "query"),
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
            String shopName,
            Integer priceRanking,
            double startingPrice,
            double closingPrice,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false)
                    Integer size) throws ParseException {

        return storeInformationService.findStoreInformationList(storeTotalType, storeSonType, cityCode, areaCode, tagId, shopName, priceRanking, startingPrice, closingPrice, size, page);
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


    @ApiOperation(value = "获取店铺图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "albumType", value = "图片类型（环境-1、其他-2）", paramType = "query"),
    })
    @GetMapping("/getShopAlbum")
    public List<McStoreAlbum> getShopAlbum(
            @NotNull(message = "编号不能为空") Long storeId,
            Integer albumType){

        Map<String, Object> map = new HashMap<>(4);
        map.put("storeId", storeId);
        if (albumType > 0){
            map.put("albumType", albumType);
        }
        return mcStoreAlbumMallService.findListByParam(map);

    }



}

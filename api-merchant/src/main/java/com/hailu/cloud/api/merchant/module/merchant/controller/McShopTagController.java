package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.entity.McShopTag;
import com.hailu.cloud.api.merchant.module.merchant.service.McShopTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺标签
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 16:
 */
@RestController
@RequestMapping("/app/tag")
@Validated
@Api(tags = "标签")
@Slf4j
public class McShopTagController {

    @Autowired
    private McShopTagService mcShopTagService;

    @ApiOperation(value = "根据店铺编号查询店铺下的标签" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺编号", required = true, paramType = "query")
    })
    @GetMapping("/shopLabel")
    public List<McShopTag> findMcShopTagListByStoreId(
            @NotNull(message = "编号不能为空") Long storeId) {


        return mcShopTagService.findMcShopTagListByStoreId(storeId);
    }

    @ApiOperation(value = "根据店铺编号添加标签" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagId", value = "标签编号", allowMultiple = true,  required = true, paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "店铺编号", required = true, paramType = "query")
    })
    @GetMapping("/addShopTag")
    public List<McShopTag> addMcSHopTag(
            @NotNull(message = "标签编号不能为空") Long[] tagId,
            @NotNull(message = "店铺编号不能为空") Long storeId) {


        return mcShopTagService.addMcSHopTag(tagId, storeId);
    }

    @ApiOperation(value = "根据店铺编号修改标签" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagId", value = "标签编号", allowMultiple = true,  required = true, paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "店铺编号", required = true, paramType = "query")
    })
    @GetMapping("/updShopTag")
    public List<McShopTag> updateMcShopTag(
            @NotNull(message = "标签编号不能为空") Long[] tagId,
            @NotNull(message = "店铺编号不能为空") Long storeId) {


        return mcShopTagService.updateMcShopTag(tagId, storeId);
    }


}

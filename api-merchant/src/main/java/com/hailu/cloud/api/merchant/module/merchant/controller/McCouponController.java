package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.entity.McCoupon;
import com.hailu.cloud.api.merchant.module.merchant.model.McCouponModel;
import com.hailu.cloud.api.merchant.module.merchant.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McCouponService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 到店卷
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 15:
 */
@RestController
@RequestMapping("/app/coupon")
@Validated
@Api(tags = "到店卷")
@Slf4j
public class McCouponController {

    @Autowired
    private McCouponService mcCouponService;

    @ApiOperation(value = "添加到店卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "picturePath", value = "图片路径", required = true,  paramType = "query", allowMultiple = true),
            @ApiImplicitParam(name = "pictureType", value = "图片类型(主图-1、特色-2)",  required = true,  paramType = "query", allowMultiple = true),
            @ApiImplicitParam(name = "submissionType", value = "提交类型(保存-1、提交审核-2)",  required = true,  paramType = "query", allowMultiple = true)
    })
    @PostMapping("/addCoupon")
    public McCoupon insertSelective(
            @RequestBody @NotNull(message = "信息不能为空") McCouponModel record,
            @RequestBody @NotNull(message = "信息不能为空") McCouponOtherJsonModel mcCouponOtherJsonModel,
            @RequestBody @NotNull(message = "图片路径不能为空") String[] picturePath,
            @RequestBody @NotNull(message = "图片类型不能为空") Integer[] pictureType,
            @RequestBody @NotNull(message = "提交类型不能为空") Integer submissionType
            ){


        return mcCouponService.insertSelective(record,mcCouponOtherJsonModel,picturePath,pictureType,submissionType);
    }


    @ApiOperation(value = "查询到店卷详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "卷编号", required = true,  paramType = "query")
    })
    @GetMapping("/viewDetails")
    public McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(
            @NotNull(message = "编号不能为空") Long id){

        return mcCouponService.selectByPrimaryKey(id);
    }


    @ApiOperation(value = "更改信息到店卷信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "卷编号", required = true,  paramType = "query")
    })
    @PostMapping("/updCoupon")
    public McCoupon updateByPrimaryKeySelective(
            @RequestBody @NotNull(message = "信息不能为空")McCoupon record,
            @RequestBody @NotNull(message = "信息不能为空")McCouponOtherJsonModel mcCouponOtherJsonModel){

        return mcCouponService.updateByPrimaryKeySelective(record,mcCouponOtherJsonModel);
    }


    @ApiOperation(value = "查询到店卷列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "toExamine", value = "审核(审核中-1,审核通过-2,审核不通过-3、待确认-4)",   paramType = "query"),
            @ApiImplicitParam(name = "volumeName", value = "卷名称",   paramType = "query"),
            @ApiImplicitParam(name = "shelfState", value = "上架状态(全部-0(可不传)、未上架-1、已上架-2、已下架-3)",   paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", required = true, defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", required = true, defaultValue = "10", paramType = "query")
    })
    @GetMapping("/findMcCouponList")
    public PageInfoModel<List<McCoupon>> findMcCouponList(
            String volumeName,
            @RequestParam(defaultValue = "0") Integer toExamine,
            @RequestParam(defaultValue = "0") Integer shelfState,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size
    ){

        return mcCouponService.findMcCouponList(volumeName,toExamine,shelfState,page,size);
    }

    @ApiOperation(value = "到店卷上下架")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "卷编号",   paramType = "query"),
            @ApiImplicitParam(name = "shelfState", value = "上架状态(已上架-2、已下架-3)",   paramType = "query"),
    })
    @PostMapping("/updShelfState")
    public void updShelfStateById(
            @NotNull(message = "编号不能为空") Long id,
            @NotNull(message = "编号不能为空") Integer shelfState){

        mcCouponService.updShelfStateById(id,shelfState);
    }

}

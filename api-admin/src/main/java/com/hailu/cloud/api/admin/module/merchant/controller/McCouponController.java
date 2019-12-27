package com.hailu.cloud.api.admin.module.merchant.controller;

import com.hailu.cloud.api.admin.module.merchant.entity.McCoupon;
import com.hailu.cloud.api.admin.module.merchant.model.McCouponModel;
import com.hailu.cloud.api.admin.module.merchant.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.admin.module.merchant.service.McCouponService;
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


    @ApiOperation(value = "查询到店卷详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "卷编号", required = true,  paramType = "query")
    })
    @GetMapping("/viewDetails")
    public McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(
            @NotNull(message = "编号不能为空") Long id){

        return mcCouponService.selectByPrimaryKey(id);
    }


    @ApiOperation(value = "查询到店卷列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeTotalType", value = "经营类型总编号",   paramType = "query"),
            @ApiImplicitParam(name = "volumeName", value = "卷名称",   paramType = "query"),
            @ApiImplicitParam(name = "toExamine", value = "审核(审核中-1,审核通过-2,审核不通过-3)",   paramType = "query"),
            @ApiImplicitParam(name = "shelfState", value = "上架状态(未上架-1、已上架-2、已下架-3)",   paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", required = true, paramType = "query")
    })
    @GetMapping("/findMcCouponList")
    public PageInfoModel<List<McCoupon>> findMcCouponList(
            Long storeTotalType,
            String volumeName,
            Integer toExamine,
            Integer shelfState,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size
    ){

        return mcCouponService.findMcCouponList(storeTotalType,volumeName,toExamine,shelfState,page,size);
    }


    @ApiOperation(value = "更改审核状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "卷编号", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "toExamine", value = "审核(审核中-1,审核通过-2,审核不通过-3)", required = true,  paramType = "query"),
    })
    @PostMapping("/updState")
    public void updMcCouponById(
            @NotNull(message = "编号不能为空") Long id,
            @NotNull(message = "状态不能为空") Integer toExamine
    ){

        mcCouponService.updMcCouponById(id,toExamine);
    }


    @ApiOperation(value = "删除到店卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "卷编号", required = true,  paramType = "query")
    })
    @DeleteMapping("/delCoupon")
    public void deleteByPrimaryKey(
            @NotNull(message = "编号不能为空") Long id
    ){

        mcCouponService.deleteByPrimaryKey(id);
    }

}

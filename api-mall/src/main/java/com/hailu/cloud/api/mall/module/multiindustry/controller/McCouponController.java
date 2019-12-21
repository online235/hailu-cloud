package com.hailu.cloud.api.mall.module.multiindustry.controller;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCoupon;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.McCouponService;
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
@RequestMapping("/app/multiIndustry")
@Validated
@Api(tags = "到店卷")
@Slf4j
public class McCouponController {

    @Autowired
    private McCouponService mcCouponService;


    @ApiOperation(value = "查询到店卷详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "卷编号", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "numberId", value = "商家编号", required = true,  paramType = "query")
    })
    @GetMapping("/viewDetails")
    public McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(
            @NotNull(message = "编号不能为空") Long id,
            @NotNull(message = "商家编号不能为空") Long numberId){

        return mcCouponService.selectByPrimaryKey(id,numberId);
    }


    @ApiOperation(value = "查询到店卷列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "商家编号",   paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", required = true, defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", required = true, defaultValue = "10", paramType = "query")
    })
    @GetMapping("/findMcCouponList")
    public PageInfoModel<List<McCoupon>> findMcCouponList(
            @NotNull(message = "商家编号不能为空") Long numberId,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size
    ){

        return mcCouponService.findMcCouponList(numberId,page,size);
    }


}

package com.hailu.cloud.api.mall.module.multiindustry.controller;


import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.service.MultiIndustryOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/multiIndustryOrder")
@Validated
@Api(tags = "商城-多行业-APP-下单")
@Slf4j
public class MultiIndustryOrderController {

    @Autowired
    private MultiIndustryOrderService orderService;

    @ApiOperation(value = "多行业下单预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "totalType", value = "店铺总类型", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "商品编号",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "memberName", value = "会员名称",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码",  required = true,  paramType = "query")

    })
    @PostMapping("/placeAnOrder")
    public void addOrder(MultiIndustryOrder order, HttpServletRequest request){
        orderService.insertSelective(order, request);
    }


    @ApiOperation(value = "用户查询多行业订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小",  required = true,  paramType = "query")
    })
    @PostMapping("/userQueryOrder")
    public Object findOrderListByMemberId(HttpServletRequest request, Integer page ,Integer size){
        return orderService.findOrderListByMemberId(request, page, size);
    }

    @ApiOperation(value = "商品查询多行业订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小",  required = true,  paramType = "query")
    })
    @PostMapping("/commodityQueryOrder")
    public Object findOrderListByStoreId(HttpServletRequest request, Integer page ,Integer size){
        return orderService.findOrderListByStoreId(request, page, size);
    }
}

package com.hailu.cloud.api.mall.module.multiindustry.controller;


import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.service.MultiIndustryOrderService;
import com.hailu.cloud.common.exception.BusinessException;
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
import java.text.ParseException;

@RestController
@RequestMapping("/app/multiIndustryOrder")
@Validated
@Api(tags = "商城-多行业-APP-订单")
@Slf4j
public class MultiIndustryOrderController {

    @Autowired
    private MultiIndustryOrderService orderService;

    @ApiOperation(value = "多行业下单预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "商品编号(现传店铺编号)",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "memberName", value = "会员名称",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码",  required = true,  paramType = "query")

    })
    @PostMapping("/placeAnOrder")
    public void addOrder(MultiIndustryOrder order, HttpServletRequest request) throws BusinessException, ParseException {
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
}

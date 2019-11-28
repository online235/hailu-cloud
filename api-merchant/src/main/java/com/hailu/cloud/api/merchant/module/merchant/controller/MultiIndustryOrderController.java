package com.hailu.cloud.api.merchant.module.merchant.controller;


import com.hailu.cloud.api.merchant.module.merchant.service.MultiIndustryOrderService;
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

package com.hailu.cloud.api.merchant.module.merchant.controller;


import com.hailu.cloud.api.merchant.module.merchant.entity.MultiIndustryOrder;
import com.hailu.cloud.api.merchant.module.merchant.service.MultiIndustryOrderService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/app/multiIndustryOrder")
@Validated
@Api(tags = "商家-多行业-APP-订单")
@Slf4j
public class MultiIndustryOrderController {

    @Autowired
    private MultiIndustryOrderService orderService;

    @ApiOperation(value = "商品查询多行业订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", required = true, defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小",  required = true, defaultValue = "20",  paramType = "query")
    })
    @PostMapping("/commodityQueryOrder")
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByStoreId(
            @RequestParam(defaultValue = "1")Integer page ,
            @RequestParam(defaultValue = "20")Integer size){

        return orderService.findOrderListByStoreId(page, size);
    }

    @ApiOperation(value = "订单状态更改已完成")
    @ApiImplicitParam(name = "id", value = "订单Id", required = true, paramType = "query")
    @PostMapping("/orderState")
    public void updateOrderState(
            @NotNull(message = "订单Id不能为空") Long id){

        orderService.updateOrderState(id);
    }


}

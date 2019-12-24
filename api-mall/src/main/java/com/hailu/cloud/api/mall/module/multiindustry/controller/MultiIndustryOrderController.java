package com.hailu.cloud.api.mall.module.multiindustry.controller;


import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.model.McOrderModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.MultiIndustryOrderService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.text.ParseException;
import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 */
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
            @ApiImplicitParam(name = "phone", value = "手机号码",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "useTime", value = "商品使用时间",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "useDate", value = "商品使用日期",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "useNumber", value = "商品使用人数",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "remarks", value = "备注",  required = true,  paramType = "query")
    })
    @PostMapping("/placeAnOrder")
    public McOrderModel addOrder(MultiIndustryOrder order){
        return orderService.insertSelective(order);
    }


    @ApiOperation(value = "用户查询多行业订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "state", value = "订单状态(全部-0、待确认-1、待使用-2、已完成-3)",  required = true,  paramType = "query")
    })
    @GetMapping("/userQueryOrder")
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByMemberId(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size,
            Integer state){
        return orderService.findOrderListByMemberId(page, size,state);
    }

    @ApiOperation(value = "用户查询订单详情")
    @ApiImplicitParam(name = "id", value = "编号", required = true,  paramType = "query")
    @GetMapping("/orderDetails")
    public McOrderModel findOrderListById(Long id){

        return orderService.selectDefaultHead(id);
    }


    @ApiOperation(value = "用户取消订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true,  paramType = "query")
    @GetMapping("/cancellationOrder")
    public void updateOrderState(Long id) throws BusinessException {

        orderService.updateOrderState(id);
    }
}

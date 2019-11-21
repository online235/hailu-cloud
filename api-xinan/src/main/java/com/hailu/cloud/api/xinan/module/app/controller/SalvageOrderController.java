package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.entity.SalvageOrder;
import com.hailu.cloud.api.xinan.module.app.service.impl.SalvageOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安-救助、订单
 * @Date: 16:49 2019/11/13 0013
 */
@RestController
@RequestMapping("/app/xinan")
@Validated
@Api(tags = "心安-救助")
@Slf4j
public class SalvageOrderController {

    @Autowired
    private SalvageOrderService salvageOrderService;

    @ApiOperation(value = "捐助时下单", notes = "<pre>" +
            "{\n" +
            "  \"errno\": 0,\n" +
            "  \"errmsg\": \"成功\"\n" +
            "}")
    @PostMapping("/rescueOrder")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "froms", value = "订单来源（XA-心安、LJ-链接）", required = true, paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "invitationMember", value = "分享者编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rescueId", value = "救助编号", required = true, paramType = "query", dataType = "String")
    })
    public void insSalvageOrder(SalvageOrder salvageOrder){
        salvageOrderService.buildOrder(salvageOrder);
    }
}

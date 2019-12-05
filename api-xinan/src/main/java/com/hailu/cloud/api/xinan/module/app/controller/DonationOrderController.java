package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.entity.Order;
import com.hailu.cloud.api.xinan.module.app.entity.SalvageOrder;
import com.hailu.cloud.api.xinan.module.app.service.impl.DonationOrderService;
import com.hailu.cloud.api.xinan.module.app.service.impl.OrderService;
import com.hailu.cloud.api.xinan.module.app.service.impl.SalvageOrderService;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安-救助、订单
 * @Date: 16:49 2019/11/13 0013
 */
@RestController
@RequestMapping("/app/xinan")
@Validated
@Api(tags = "心安-捐赠下单")
@Slf4j
public class DonationOrderController {

    @Autowired
    private DonationOrderService donationOrderService;

    @ApiOperation(value = "捐助时下单", notes = "<pre>" +
            "{\n" +
            "  \"errno\": 0,\n" +
            "  \"errmsg\": \"成功\"\n" +
            "}")
    @PostMapping("/donationOrder")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", value = "订单来源（XA-心安、LJ-链接）", required = true, paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "invitationMember", value = "分享者编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rescueId", value = "捐赠编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "itemType", value = "捐赠类型(政府慈善-1、救助-2、互助-3)", required = true, paramType = "query", dataType = "Integer")
    })
    public Order insSalvageOrder(
            @NotBlank(message = "捐赠编号不能为空") String rescueId,
            @NotNull(message = "捐赠类型不能为空") Integer itemType,
            @NotBlank(message = "订单来源不能为空") String from,
            @NotBlank(message = "金额不能为空") BigDecimal money, String invitationMember){


        return donationOrderService.donationOrder(rescueId, itemType, from, money, invitationMember);
    }
}

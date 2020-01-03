package com.hailu.cloud.api.mall.module.logistics.controller;

import com.hailu.cloud.api.mall.module.logistics.model.callbackDataModel;
import com.hailu.cloud.api.mall.module.logistics.model.callbackModel;
import com.hailu.cloud.api.mall.module.logistics.service.impl.HomeDeliveryImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.ParseException;

/**
 * @Author: QiuFeng:WANG
 * @Description: 宅急送
 * @Date: 2019/12/31 0031
 * @program: cloud
 * @create: 2019-12-31 10:
 */
@RestController
@RequestMapping("/app/homeDelivery")
@Validated
@Api(tags = "宅急送")
@Slf4j
public class HomeDeliveryController {

    @Autowired
    private HomeDeliveryImpl homeDeliveryImpl;

    @ApiOperation(value = "查询订单轨迹")
    @GetMapping("/findQueryStatus")
    public Object findQueryStatus() throws IOException {
        return homeDeliveryImpl.find();
    }

    @ApiOperation(value = "回调")
    @PostMapping("/callback")
    public Object callback(@RequestBody @NotNull(message = "信息不能为空") callbackModel<callbackDataModel> callbackModel){

        return homeDeliveryImpl.callback(callbackModel);
    }



}

package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.model.CouponAndReceiveModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McCouponReceiveService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 优惠卷
 * @Date: 2019/12/24 0024
 * @program: cloud
 * @create: 2019-12-24 15:
 */
@RestController
@RequestMapping("/app/multiIndustryReceive")
@Validated
@Api(tags = "到店卷")
@Slf4j
public class McCouponReceiveController {

    @Autowired
    private McCouponReceiveService mcCouponReceiveService;


    @ApiOperation(value = "商户查询用户到店卷列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usageState", value = "使用状态(待使用-1、已核销-2)", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", required = true, defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", required = true, defaultValue = "10", paramType = "query")
    })
    @GetMapping("/receiveList")
    public PageInfoModel<List<CouponAndReceiveModel>> findMcCouponByMemberIdList(
            Integer usageState,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size
    ){

        return mcCouponReceiveService.findMcCouponByMemberIdList(usageState,page,size);
    }

    @ApiOperation(value = "核销优惠卷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "awardCode", value = "兑奖码", required = true,  paramType = "query")
    })
    @GetMapping("/confirm")
    public void updUsageStateByAwardCodeAndStoreId(
            @NotBlank(message = "兑奖码不能为空") String awardCode
    ) throws BusinessException {

        mcCouponReceiveService.updUsageStateByAwardCodeAndStoreId(awardCode);
    }
}

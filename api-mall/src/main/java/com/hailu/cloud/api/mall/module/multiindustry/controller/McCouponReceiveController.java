package com.hailu.cloud.api.mall.module.multiindustry.controller;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCouponReceive;
import com.hailu.cloud.api.mall.module.multiindustry.model.CouponAndReceiveModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponReceiveModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.McCouponReceiveService;
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

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
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

    @ApiOperation(value = "用户领取到店卷")
    @PostMapping("/receive")
    public McCouponReceive insertSelective(@RequestBody McCouponReceiveModel model) throws BusinessException {

        return mcCouponReceiveService.insertSelective(model);
    }

    @ApiOperation(value = "用户查询优惠卷列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usageState", value = "使用状态(待使用-1、已使用-2、已失效-3)",  paramType = "query"),
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

    @ApiOperation(value = "用户查询优惠卷详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiveId", value = "优惠卷编号", required = true,  paramType = "query")
    })
    @GetMapping("/detailed")
    public CouponAndReceiveModel<McCouponOtherJsonModel> findMcCouponByReceiveId(
            @NotNull(message = "编号不能为空") Long receiveId
    ){

        return mcCouponReceiveService.findMcCouponByReceiveId(receiveId);
    }
}

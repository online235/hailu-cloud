package com.hailu.cloud.api.mall.module.multiindustry.controller;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McCouponPicture;
import com.hailu.cloud.api.mall.module.multiindustry.service.McCouponPictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 到店卷图片
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 15:
 */
@RestController
@RequestMapping("/app/multiIndustry")
@Validated
@Api(tags = "到店卷")
@Slf4j
public class McCouponPictureController {

    @Autowired
    private McCouponPictureService mcCouponPictureService;


    @ApiOperation(value = "查询图片列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponId", value = "卷编号", required = true,  paramType = "query")
    })
    @GetMapping("/findPictureList")
    public List<McCouponPicture> findMcCouponPictureListByCouponId(
            @NotNull(message = "卷编号不能为空") Long couponId
    ){

        return mcCouponPictureService.findMcCouponPictureListByCouponId(couponId);
    }


}

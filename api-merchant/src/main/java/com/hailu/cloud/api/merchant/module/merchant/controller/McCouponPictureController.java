package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.entity.McCouponPicture;
import com.hailu.cloud.api.merchant.module.merchant.service.McCouponPictureService;
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
@RequestMapping("/app/coupon")
@Validated
@Api(tags = "到店卷管理")
@Slf4j
public class McCouponPictureController {

    @Autowired
    private McCouponPictureService mcCouponPictureService;

    @ApiOperation(value = "添加单张到店卷图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "picturePath", value = "图片路径", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "pictureType", value = "图片类型", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "couponId", value = "卷编号", required = true,  paramType = "query")
    })
    @PostMapping("/updCouponPicture")
    public McCouponPicture insertMcCouponPicture(
            @NotNull(message = "图片路径不能为空") String picturePath,
            @NotNull(message = "图片类型不能为空") Integer pictureType,
            @NotNull(message = "卷编号不能为空") Long couponId
    ){

        return mcCouponPictureService.insertMcCouponPicture(picturePath,pictureType,couponId);
    }

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


    @ApiOperation(value = "删除单张图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图片编号", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "couponId", value = "卷编号", required = true,  paramType = "query")
    })
    @DeleteMapping("/delPicture")
    public void deleteByPrimaryKey(
            @NotNull(message = "图片编号不能为空") Long id,
            @NotNull(message = "卷编号不能为空") Long couponId
    ){

        mcCouponPictureService.deleteByPrimaryKey(id,couponId);
    }

}

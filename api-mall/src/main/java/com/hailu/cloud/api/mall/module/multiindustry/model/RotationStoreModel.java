package com.hailu.cloud.api.mall.module.multiindustry.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("店铺轮播图")
@Data
public class RotationStoreModel {

    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id")
    private Long id;


    /**
     * 相册路径
     */
    @ApiModelProperty(value = "相册路径")
    private String albumUrl;



}

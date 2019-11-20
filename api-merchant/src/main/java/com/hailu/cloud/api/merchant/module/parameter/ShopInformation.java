package com.hailu.cloud.api.merchant.module.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author zhangmugui
 * @Date 2019.11.19
 */
@Data
@ApiModel
public class ShopInformation {

    @ApiParam(name = "shopName", value = "店铺名称", required = true)
    @NotEmpty
    private String shopName;

    @ApiParam(name = "phone", value = "店铺电话", required = true)
    @NotEmpty
    private String phone;

    @ApiParam(name = "largeType", value = "一级经营名称", required = true)
    @NotEmpty
    private String largeType;

    /**
     * 有可能只有一级经营名称
     */
    @ApiParam(name = "smallType", value = "二级经营名称", required = false)
    private String smallType;


    @ApiParam(name = "provinceCode", value = "省的code值", required = true)
    @NotEmpty
    private String provinceCode;


    @ApiParam(name = "cityCode", value = "城市的code值", required = true)
    @NotEmpty
    private String cityCode;


    @ApiParam(name = "areaCode", value = "区的code值", required = true)
    @NotEmpty
    private String areaCode;


    @ApiParam(name = "detailAddress", value = "详细地址", required = true)
    @NotEmpty
    private String detailAddress;


}

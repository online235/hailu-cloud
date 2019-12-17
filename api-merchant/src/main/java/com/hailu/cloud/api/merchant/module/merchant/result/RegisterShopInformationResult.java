package com.hailu.cloud.api.merchant.module.merchant.result;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("已填资料")
@Data
public class RegisterShopInformationResult {


    @ApiModelProperty(name = "firstManagementTypeId", value = "一级经营类型id")
    private Long firstManagementTypeId;


    @ApiModelProperty(name = "provinceCode", value = "门店省的code值")
    private String provinceCode;


    @ApiModelProperty(name = "cityCode", value = "门店城市的code值")
    private String cityCode;


    @ApiModelProperty(name = "areaCode", value = "门店区的code值")
    private String areaCode;


    @ApiModelProperty(name = "detailAddress", value = "门店详细地址")
    private String detailAddress;


}

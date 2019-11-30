package com.hailu.cloud.api.merchant.module.merchant.parameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class McStoreInformationModel {



    @ApiParam(name = "id", value = "编号不能为空", required = true)
    @NotNull(message = "编号不能为空")
    private Long id;


    @ApiParam(name = "mcNumberId", value = "商家编号")
    private String mcNumberId;


    @ApiParam(name = "shopName", value = "店铺名称")
    private String shopName;



    @ApiParam(name = "phone", value = "店铺联系电话")
    private String phone;

    @ApiParam(name = "provinceCode", value = "省Id")
    private String provinceCode;

    @ApiParam(name = "cityCode", value = "市Id")
    private String cityCode;


    @ApiParam(name = "areaCode", value = "区id")
    private String areaCode;


    @ApiParam(name = "detailAddress", value = "店铺详细地址")
    private String detailAddress;


    @ApiParam(name = "storeDetails", value = "店铺详情")
    private String storeDetails;


    @ApiParam(name = "perCapitaPrice", value = "人均价格")
    private java.math.BigDecimal perCapitaPrice;



    @ApiParam(name = "businessState", value = "营业状态(1-营业中，2-休息中)")
    private Integer businessState;



    @ApiParam(name = "closingTime", value = "关闭时间")
    private String closingTime;



    @ApiParam(name = "openingTime", value = "开店时间")
    private String openingTime;


    @ApiParam(name = "toExamine", value = "审核中-1'',''审核通过-2'',''审核不通过-3")
    private Integer toExamine;


    @ApiParam(name = "weekDay", value = "每周营业日用（1星期日，2星期一）")
    private String weekDay;


}

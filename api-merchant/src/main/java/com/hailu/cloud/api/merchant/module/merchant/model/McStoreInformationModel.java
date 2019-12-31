package com.hailu.cloud.api.merchant.module.merchant.model;

import com.hailu.cloud.common.fill.annotation.DictName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class McStoreInformationModel {


    @ApiModelProperty(name = "id", value = "编号不能为空", required = true)
    @NotNull(message = "编号不能为空")
    private Long id;


    @ApiModelProperty(name = "mcNumberId", value = "商家编号")
    private String mcNumberId;


    @ApiModelProperty(name = "shopName", value = "店铺名称")
    private String shopName;



    @ApiModelProperty(name = "phone", value = "店铺联系电话")
    private String phone;

    @ApiModelProperty(name = "provinceCode", value = "省code")
    private String provinceCode;

    @ApiModelProperty(name = "cityCode", value = "市code")
    private String cityCode;


    @ApiModelProperty(name = "areaCode", value = "区code")
    private String areaCode;

    @ApiModelProperty(name = "streetCode", value = "街道code")
    private String streetCode;


    @ApiModelProperty(name = "detailAddress", value = "店铺详细地址")
    private String detailAddress;


    @ApiModelProperty(name = "storeDetails", value = "店铺详情")
    private String storeDetails;


    @ApiModelProperty(name = "perCapitaPrice", value = "人均价格")
    private java.math.BigDecimal perCapitaPrice;


    @ApiModelProperty(name = "minPrice", value = "最低消费")
    private java.math.BigDecimal minPrice;


    @ApiModelProperty(name = "businessState", value = "营业状态(1-营业中，2-休息中)")
    private Integer businessState;


    /**
     * 经营时间，多段“,”拼接;例如“08:00-12:00,14:00-16:00”
     */
    @ApiModelProperty("经营时间，多段“,”拼接;例如“08:00-12:00,14:00-16:00”")
    private String businessTime;



    @ApiModelProperty(name = "toExamine", value = "审核中-1'',''审核通过-2'',''审核不通过-3")
    private Integer toExamine;


    @ApiModelProperty(name = "weekDay", value = "每周营业日用（1星期日，2星期一）")
    private String weekDay;

    /**
     *店铺位置经度
     */
    @ApiModelProperty("店铺位置经度")
    private String longitude;


    /**
     *店铺纬度
     */
    @ApiModelProperty("店铺纬度")
    private String latitude;

    /**
     * '1、生活圈入驻店铺；2、百货入驻店铺'
     */
    private Integer accountType;


    @DictName(code = "ACCOUNT_TYPE" , joinField = "accountType")
    private String accountTypeDisplay;


}

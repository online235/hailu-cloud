package com.hailu.cloud.api.merchant.module.merchant.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author zhangmugui
 */
@Data
@ApiModel("审核参数")
public class McStoreExamineParameter {


    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺id",required = true)
    private Long storeId;


    /**
     * 店铺名
     */
    @ApiModelProperty("店铺名")
    private String storeName;


    /**
     * 店铺电话号码
     */
    @ApiModelProperty("店铺电话号码")
    private String shopPhone;

    /**
     * 店铺详细地址
     */
    @ApiModelProperty("店铺详细地址")
    private String shopAddressDetail;

    /**
     * 店铺地区选择
     */
    @ApiModelProperty("店铺区选择")
    private String areaCode;

    /**
     * 店铺名凭证
     */
    @ApiModelProperty("店铺名凭证")
    private String storeNameVoucher;

    /**
     * 电话凭证
     */
    @ApiModelProperty("电话凭证")
    private String phoneVoucher;

    /**
     * 地址凭证
     */
    @ApiModelProperty("地址凭证")
    private String addressVoucher;

    /**
     * 地址备注
     */
    @ApiModelProperty("地址备注")
    private String addressRemarks;

    /**
     * 店铺名备注
     */
    @ApiModelProperty("店铺名备注")
    private String storeNameRemarks;


    /**
     * 电话备注
     */
    @ApiModelProperty("电话备注")
    private String phoneRemarks;



}

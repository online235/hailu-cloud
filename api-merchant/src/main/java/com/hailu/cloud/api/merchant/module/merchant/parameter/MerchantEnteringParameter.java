package com.hailu.cloud.api.merchant.module.merchant.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class MerchantEnteringParameter {


    @ApiModelProperty(name = "mcType", value = "1、个体店 ； 2、个体工商店 ； 3、企业店")
    private Integer mcType;

    @ApiModelProperty(name = "shopName", value = "店铺名称", required = true)
    @NotEmpty
    private String shopName;

    @ApiModelProperty(name = "phone", value = "店铺电话", required = true)
    @NotEmpty
    private String phone;

    @ApiModelProperty(name = "firstManagementTypeId", value = "一级经营类型id")
    private Long firstManagementTypeId;

    /**
     * 身份证正面
     */
    @ApiModelProperty(name = "idcardImgx", value = "身份证正面", required = true)
    @NotEmpty
    private String idcardImgx;

    /**
     * 身份证反面
     */
    @ApiModelProperty(name = "idcardImgy", value = "身份证反面", required = true)
    @NotEmpty
    private String idcardImgy;


    /**
     * 营业执照正面照
     */
    @ApiModelProperty(name = "licensePositive", value = "营业执照正面照", required = true)
    private String licensePositive;


    /**
     * 营业状态(1-营业中，2-(筹建中或休息中)
     */
    @ApiModelProperty(name = "businessState", value = "营业状态(1-营业中，2-(筹建中或休息中)")
    private Integer businessState;


//
//
//    @ApiModelProperty(name = "secondManagementTypeId", value = "二级经营类型id")
//    private Long secondManagementTypeId;


//    @ApiModelProperty(name = "provinceCode", value = "省的code值", required = true)
//    @NotEmpty
//    private String provinceCode;
//
//
//    @ApiModelProperty(name = "cityCode", value = "城市的code值", required = true)
//    @NotEmpty
//    private String cityCode;
//
//
//    @ApiModelProperty(name = "areaCode", value = "区的code值", required = true)
//    @NotEmpty
//    private String areaCode;
//
//
//    @ApiModelProperty(name = "detailAddress", value = "详细地址", required = true)
//    @NotEmpty
//    private String detailAddress;

//
//    @ApiModelProperty(name = "nameOfLegalPerson", value = "经营者姓名", required = true)
//    @NotEmpty
//    private String nameOfLegalPerson;


//
//    @ApiModelProperty(name = "idCard", value = "经营者身份证号码")
//    @NotEmpty
//    private String idCard;


//    /**
//     * 营业执照注册号
//     */
//    @ApiModelProperty(name = "businessLicenseNumber", value = "营业执照注册号")
//    private String businessLicenseNumber;


//    /**
//     * 附件数组字符串（用；隔开）
//     */
//    @ApiModelProperty(name = "enclosures", value = "附件数组字符串（用；隔开）")
//    private String enclosures;


//    /**
//     * 第三方链接
//     */
//    @ApiModelProperty(name = "thirdPartyLinks", value = "第三方链接")
//    private String thirdPartyLinks;

//
//    /**
//     *开户类型（1、储蓄卡；2、借记卡 ）
//     */
//    @ApiModelProperty(name = "bankType", value = "开户类型（1、储蓄卡；2、借记卡 ）")
//    private Integer bankType;

//    /**
//     * 开户支行
//     */
//    @ApiModelProperty(name = "bankBranch", value = "开户支行")
//    private String bankBranch;


//    /**
//     * 开户账号
//     */
//    @ApiModelProperty(name = "accountNumber", value = "开户账号")
//    private String accountNumber;
//
//    /**
//     * 开户省份code
//     */
//    @ApiModelProperty(name = "bankProvince", value = "开户省份code")
//    private String bankProvince;
//
//    /**
//     * 开户市区code
//     */
//    @ApiModelProperty(name = "bankCity", value = "开户市区code")
//    private String bankCity;

//    /**
//     * 开票方式（1、平台代开；2、商家代开；3、各开各票）
//     */
//    @ApiModelProperty(name = "billingMethod", value = "开票方式（1、平台代开；2、商家代开；3、各开各票）")
//    private Integer billingMethod;

//
//    /**
//     * 开户行
//     */
//    @ApiModelProperty(name = "bank", value = "开户行")
//    private String bank;


//    /**
//     * 银行卡号
//     */
//    @ApiModelProperty(name = "bankNumber", value = "银行卡号")
//    private Long bankNumber;


}

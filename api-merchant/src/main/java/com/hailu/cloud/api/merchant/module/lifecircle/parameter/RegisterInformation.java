package com.hailu.cloud.api.merchant.module.lifecircle.parameter;


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
public class RegisterInformation {

    @ApiParam(name = "shopName", value = "店铺名称", required = true)
    @NotEmpty
    private String shopName;

    @ApiParam(name = "phone", value = "店铺电话", required = true)
    @NotEmpty
    private String phone;

    @ApiParam(name = "firstManagementTypeId", value = "一级经营类型id", required = true)
    @NotEmpty
    private Integer firstManagementTypeId;


    @ApiParam(name = "secondManagementTypeId", value = "二级经营类型id", required = true)
    private Integer secondManagementTypeId;


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



    @ApiParam(name = "nameOfLegalPerson", value = "经营者姓名", required = true)
    @NotEmpty
    private String nameOfLegalPerson;


    @ApiParam(name = "idCard", value = "经营者身份证号码", required = true)
    @NotEmpty
    private String idCard;


    /**
     * 身份证正面
     */
    @ApiParam(name = "idcardImgx", value = "身份证正面", required = true)
    @NotEmpty
    private String idcardImgx;

    /**
     * 身份证反面
     */
    @ApiParam(name = "idcardImgy", value = "身份证反面", required = true)
    @NotEmpty
    private String idcardImgy;


    /**
     * 营业执照注册号
     */
    @ApiParam(name = "businessLicenseNumber", value = "营业执照注册号", required = true)
    @NotEmpty
    private String businessLicenseNumber;


    /**
     * 营业执照正面照
     */
    @ApiParam(name = "licensePositive", value = "营业执照正面照", required = true)
    @NotEmpty
    private String licensePositive;

    /**
     * 附件数组字符串（用；隔开）
     */
    @ApiParam(name = "enclosures", value = "附件数组字符串（用；隔开）", required = false)
    private String enclosures;


    /**
     * 第三方链接
     */
    @ApiParam(name = "thirdPartyLinks", value = "第三方链接",required = false)
    private String thirdPartyLinks;


//
//    /**
//     * (账号)开户类型（1、借记卡；2、信用卡 ）
//     */
//    @ApiParam(name = "bankType", value = "开户类型（1、借记卡；2、信用卡 ）", required = true)
//    private Integer bankType;
//
//    /**
//     * 开户支行
//     */
//    @ApiParam(name = "bankBranch", value = "开户支行", required = true)
//    private String bankBranch;


//    /**
//     * 开票方式（1、平台代开；2、商家代开；3、各开各票）
//     */
//    @ApiParam(name = "billingMethod", value = "开票方式（1、平台代开；2、商家代开；3、各开各票）", required = true)
//    private Integer billingMethod;


//    /**
//     * 开户行
//     */
//    @ApiParam(name = "bank", value = "开户行", required = true)
//    private String bank;


//    /**
//     * 银行卡号
//     */
//    @ApiParam(name = "bankNumber", value = "银行卡号", required = true)
//    private String bankNumber;



    /**
     * 注册验证码
     */
    @ApiParam(name = "code", value = "注册验证码", required = true)
    private String code;

    /**
     * 注册登陆账号
     */
    @ApiParam(name = "landingaccount", value = "注册登陆账号", required = true)
    private String landingAccount;


    /**
     * 注册登陆密码
     */
    @ApiParam(name = "landingpassword", value = "注册登陆密码", required = true)
    private String landingPassword;


    /**
     * 注册手机号码
     */
    @ApiParam(name = "moli", value = "注册手机号码", required = true)
    private String moli;


}

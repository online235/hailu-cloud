package com.hailu.cloud.api.merchant.module.merchant.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class McQualifications {


    /**
     * 编号
     */
    @ApiParam(name = "numberId", value = "店铺编号", required = true)
    @NotEmpty
    private String numberId;



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
     * 第三方链接
     */
    @ApiParam(name = "thirdPartyLinks", value = "第三方链接",required = false)
    private String thirdPartyLinks;


    /**
     * 开户行
     */
    @ApiParam(name = "bank", value = "开户行", required = true)
    private String bank;


    /**
     * 银行卡号
     */
    @ApiParam(name = "bankNumber", value = "银行卡号", required = true)
    private String bankNumber;


}

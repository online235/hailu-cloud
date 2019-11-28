package com.hailu.cloud.api.admin.module.merchant.parmeter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author zhangmugui
 * @Date 2019.11.19
 */

@ApiModel
@Data
public class UpdateLocalCircleEntryParameter {


    /**
     * 编号
     */
    @ApiParam(name = "numberId", value = "入驻编号", required = true)
    @NotEmpty
    private String numberId;



    @ApiParam(name = "shopName", value = "店铺名称")
    private String shopName;

    @ApiParam(name = "phone", value = "店铺电话")
    private String phone;

    @ApiParam(name = "firstManagementTypeId", value = "一级经营类型id")
    private Long firstManagementTypeId;


    @ApiParam(name = "secondManagementTypeId", value = "二级经营类型id")
    private Long secondManagementTypeId;


    @ApiParam(name = "provinceCode", value = "省的code值")
    private String provinceCode;


    @ApiParam(name = "cityCode", value = "城市的code值")
    private String cityCode;


    @ApiParam(name = "areaCode", value = "区的code值")
    private String areaCode;


    @ApiParam(name = "detailAddress", value = "详细地址")
    private String detailAddress;



    @ApiParam(name = "nameOfLegalPerson", value = "经营者姓名")
    private String nameOfLegalPerson;


    @ApiParam(name = "idCard", value = "经营者身份证号码")
    private String idCard;


    /**
     * 身份证正面
     */
    @ApiParam(name = "idcardImgx", value = "身份证正面")
    private String idcardImgx;

    /**
     * 身份证反面
     */
    @ApiParam(name = "idcardImgy", value = "身份证反面")
    private String idcardImgy;


    /**
     * 营业执照注册号
     */
    @ApiParam(name = "businessLicenseNumber", value = "营业执照注册号")
    private String businessLicenseNumber;


    /**
     * 营业执照正面照
     */
    @ApiParam(name = "licensePositive", value = "营业执照正面照")
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



}

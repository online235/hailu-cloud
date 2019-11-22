package com.hailu.cloud.api.merchant.module.lifecircle.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocalCircleEntry implements Serializable {
    /**
     * 编号
     */
    private String numberId;

    /**
     * 商家编号
     */
    private String mcNumberId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 主营类目
     */
    private String projectType;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 身份证正面
     */
    private String idcardImgx;

    /**
     * 身份证反面
     */
    private String idcardImgy;

    /**
     * 身份证有效期
     */
    private String idcardtermofValidity;

    /**
     * 身份证是否为长期
     */
    private Boolean longTermCertificate;

    /**
     * 一级经营类型表id
     */
    private Integer firstManagementTypeId;


    /**
     * 二级经营类型表id
     */
    private Integer secondManagementTypeId;



    /**
     * 营业执照注册号
     */
    private String businessLicenseNumber;

    /**
     * 执照名称
     */
    private String businessName;

    /**
     * 法人姓名
     */
    private String nameOfLegalPerson;

    /**
     * 执照有效日期
     */
    private String licenseDate;

    /**
     * 营业执照是否为长期
     */
    private Boolean longTermLicense;

    /**
     * 营业执照正面照
     */
    private String licensePositive;

    /**
     * 第三方链接
     */
    private String thirdPartyLinks;

    /**
     * 入驻邀请码
     */
    private String invitationCode;

    /**
     * 是否为服务商
     */
    private Boolean serviceProviderOrNot;

    /**
     * 审核
     */
    private String toExamine;

    /**
     * 市Id
     */
    private Long cityCode;

    /**
     * 省Id
     */
    private Long provinceCode;

    /**
     * 区Id
     */
    private Long areaCode;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private Long createdat;

    /**
     * 更改时间
     */
    private Long updatedat;

//    /**
//     * 开户银行
//     */
//    private String bank;

//    /**
//     * 开户支行
//     */
//    private Integer bankBranch;


//    /**
//     * 开票方式（1、平台代开；2、商家代开；3、各开各票）
//     */
//    private Integer billingMethod;

//
//    /**
//     * 银行卡号
//     */
//    private String bankNumber;

//    /**
//     * 开户类型（1、借记卡；2、信用卡 ）
//     */
//    private Integer bankType;

    /**
     * 附件数组字符串（用；隔开）
     */
    private String enclosures;


    /**
     * mc_entry_information
     */
    private static final long serialVersionUID = 1L;
}
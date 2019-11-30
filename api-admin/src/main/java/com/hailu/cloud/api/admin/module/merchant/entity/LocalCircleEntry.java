package com.hailu.cloud.api.admin.module.merchant.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangmugui
 */
@Data
@InjectDict
public class LocalCircleEntry implements Serializable {

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String numberId;

    /**
     * 商家编号
     */
    @ApiModelProperty("商家编号")
    private String mcNumberId;

    /**
     * 店铺名称
     */
    @ApiModelProperty("店铺名称")
    private String shopName;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 身份证号码
     */
    @ApiModelProperty("身份证号码")
    private String idCard;

    /**
     * 身份证正面
     */
    @ApiModelProperty("身份证正面")
    private String idcardImgx;

    /**
     * 身份证反面
     */
    @ApiModelProperty("身份证反面")
    private String idcardImgy;

    /**
     * 身份证有效期
     */
    @ApiModelProperty("身份证有效期")
    private String idcardtermofValidity;

    /**
     * 身份证是否为长期
     */
    @ApiModelProperty("身份证是否为长期")
    private Integer longTermCertificate;

    /**
     * 经营类型表一级编号
     */
    @ApiModelProperty("经营类型表一级编号")
    private Long firstManagementTypeId;

    /**
     * 经营类型表二级编号
     */
    @ApiModelProperty("经营类型表二级编号")
    private Long secondManagementTypeId;

    /**
     * 营业执照注册号
     */
    @ApiModelProperty("营业执照注册号")
    private String businessLicenseNumber;

    /**
     * 执照名称
     */
    @ApiModelProperty("执照名称")
    private String businessName;

    /**
     * 法人姓名
     */
    @ApiModelProperty("法人姓名")
    private String nameOfLegalPerson;

    /**
     * 执照有效日期
     */
    @ApiModelProperty("执照有效日期")
    private String licenseDate;

    /**
     * 营业执照是否为长期
     */
    @ApiModelProperty("营业执照是否为长期")
    private Integer longTermLicense;

    /**
     * 营业执照正面照
     */
    @ApiModelProperty("营业执照正面照")
    private String licensePositive;

    /**
     * 第三方链接
     */
    @ApiModelProperty("第三方链接")
    private String thirdPartyLinks;

    /**
     * 入驻邀请码
     */
    @ApiModelProperty("入驻邀请码")
    private String invitationCode;

    /**
     * 是否为服务商
     */
    @ApiModelProperty("是否为服务商")
    private Integer serviceProviderOrNot;

    /**
     * 审核（'1 审核中','2 审核通过','3 审核不通过'）
     */
    @ApiModelProperty("审核（'1 审核中','2 审核通过','3 审核不通过'）")
    private Integer toExamine;

    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    private String toExamineDisPlay;

    /**
     * 省Id
     */
    @ApiModelProperty("省Id")
    private String provinceCode;

    /**
     * 市Id
     */
    @ApiModelProperty("市Id")
    private String cityCode;

    /**
     * 区id
     */
    @ApiModelProperty("区id")
    private String areaCode;

    /**
     * 店铺详细地址
     */
    @ApiModelProperty("店铺详细地址")
    private String detailAddress;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createdat;



    /**
     * 更改时间
     */
    @ApiModelProperty("更改时间")
    private Long updatedat;


    /**
     * 附件数组（用,隔开）
     */
    @ApiModelProperty("附件数组（用,隔开）")
    private String enclosures;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更改时间
     */
    @ApiModelProperty("更改时间")
    private Date updateTime;



    public void setCreatedat(Long createdat) {
        this.createdat = createdat;
        this.createTime = DateUtil.date(createdat);
    }



    public void setUpdatedat(Long updatedat) {
        this.updatedat = updatedat;
        this.updateTime = DateUtil.date(updatedat);
    }



}

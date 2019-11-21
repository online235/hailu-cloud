package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class McEntryinFormation implements Serializable {
    /**
     * 编号
     */
    private String numberid;

    /**
     * 商家编号
     */
    private String mcnumberid;

    /**
     * 店铺名称
     */
    private String shopname;

    /**
     * 主营类目
     */
    private String projecttype;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 身份证正面
     */
    private String idcardimgx;

    /**
     * 身份证反面
     */
    private String idcardimgy;

    /**
     * 身份证有效期
     */
    private String idcardtermofvalidity;

    /**
     * 身份证是否为长期
     */
    private boolean longtermcertificate;

    /**
     * 执照名称
     */
    private String businessname;

    /**
     * 法人姓名
     */
    private String nameoflegalperson;

    /**
     * 执照有效日期
     */
    private String licensedate;

    private boolean longtermlicense;

    /**
     * 营业执照正面照
     */
    private String licensepositive;

    /**
     * 审核
     */
    private String toexamine;

    /**
     * 创建时间
     */
    private Long createdat;

    /**
     * 更改时间
     */
    private Long updatedat;

    /**
     * 营业执照注册号
     */
    private String businesslicensenumber;

    /**
     * 是否为服务商
     */
    private Integer serviceProviderOrNot;

    /**
     * 备注
     */
    private String remarks;

    /**
     * mc_entryinformation
     */
    private static final long serialVersionUID = 1L;


}
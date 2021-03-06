package com.hailu.cloud.api.merchant.module.merchant.entity;
import lombok.Data;
import java.util.Date;

/**
 * 店铺地址、电话审批表
 *
 * @author zhijie
 */
@Data
public class McStoreExamine {

    /**
     * 店铺地址、电话审批表id
     */
    private Long id;

    /**
     * 店铺id
     */
    private Long storeId;

    /**
     * 店铺名
     */
    private String storeName;

    /**
     * 店铺电话号码
     */
    private String shopPhone;

    /**
     * 店铺详细地址
     */
    private String shopAddressDetail;

    /**
     * 店铺地区code
     */
    private String areaCode;


    /**
     * 店铺街道code
     */
    private String streetCode;

    /**
     * 店铺名凭证
     */
    private String storeNameVoucher;

    /**
     * 电话凭证
     */
    private String phoneVoucher;

    /**
     * 地址凭证
     */
    private String addressVoucher;

    /**
     * 地址备注
     */
    private String addressRemarks;

    /**
     * 店铺名备注
     */
    private String storeNameRemarks;

    /**
     * 电话备注
     */
    private String phoneRemarks;

    /**
     * 店铺名审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')
     */
    private Integer storeNameExamine;

    /**
     * 电话审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')
     */
    private Integer phoneToExamine;

    /**
     * 地区审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')
     */
    private Integer addressToExamine;

    /**
     * 创建时间
     */
    private Date dateTime;

}

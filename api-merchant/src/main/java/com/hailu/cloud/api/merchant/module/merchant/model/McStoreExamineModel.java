package com.hailu.cloud.api.merchant.module.merchant.model;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 店铺地址、电话审批表
 *
 * @author zhijie
 */
@Data
@ApiModel
public class McStoreExamineModel {

    /**
     * 店铺地址、电话审批表id
     */
    @ApiModelProperty("店铺地址、电话审批表id")
    private Long id;

    /**
     * 店铺id
     */
    @ApiModelProperty("店铺id")
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
    @ApiModelProperty("店铺地区选择")
    private String areaCode;

    /**
     * 店铺街道code
     */
    @ApiModelProperty("店铺街道code")
    private String streetCode;

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

    /**
     * 店铺名审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')
     */
    @ApiModelProperty("店铺名审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')")
    private Integer storeNameExamine;

    /**
     * 电话审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')
     */
    @ApiModelProperty("电话审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')")
    private Integer phoneToExamine;

    /**
     * 地区审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')
     */
    @ApiModelProperty("地区审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')")
    private Integer addressToExamine;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date dateTime;

}

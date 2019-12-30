package com.hailu.cloud.api.merchant.module.merchant.result;


import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangmugui
 */
@Data
@ApiModel("审核数据以及状态")
@InjectDict
public class ExamineResult {


    @ApiModelProperty("未提交审核-0,审核中-1'',''审核通过-2'',''审核不通过-3")
    private Integer toExamine;


    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    @ApiModelProperty("'审核中-1'',''审核通过-2'',''审核不通过-3'")
    private String toExamineDisplay;


    @ApiModelProperty("原来店铺名称")
    private String originalShopName;


    @ApiModelProperty("原来店铺电话号码")
    private String originalShopPhone;

    @ApiModelProperty("原来店铺省区Code")
    private String originalProvinceCode;

    @ApiModelProperty("原来店铺市区Code")
    private String originalCityCode;

    @ApiModelProperty("原来店铺区Code")
    private String originalAreaCode;


    @ApiModelProperty("原来店铺详细地址")
    private String originalShopAddressDetail;


    @ApiModelProperty("店铺id")
    private Long storeId;

    @ApiModelProperty("店铺名")
    private String storeName;

    @ApiModelProperty("店铺电话号码")
    private String shopPhone;

    @ApiModelProperty("店铺详细地址")
    private String shopAddressDetail;


    @ApiModelProperty("店铺地区选择")
    private String areaCode;

    @ApiModelProperty("店铺名凭证")
    private String storeNameVoucher;

    @ApiModelProperty("电话凭证")
    private String phoneVoucher;


    @ApiModelProperty("地址凭证")
    private String addressVoucher;

    @ApiModelProperty("店铺名备注")
    private String storeNameRemarks;


    @ApiModelProperty("地址备注")
    private String addressRemarks;


    @ApiModelProperty("电话备注")
    private String phoneRemarks;


}

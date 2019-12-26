package com.hailu.cloud.api.admin.module.merchant.parmeter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

/**
 * @author zhngmugui
 */
@ApiModel
@Data
public class McStoreExamineListParameter {


    /**
     * 商家编号
     */
    @ApiParam(name = "storeId", value = "店铺id")
    private Long storeId;


    /**
     * 店铺名
     */
    @ApiParam("店铺名")
    private String storeName;


    /**
     * 店铺电话号码
     */
    @ApiParam("店铺电话号码")
    private String shopPhone;

    @ApiParam("创建时间,'%Y-%m-%d")
    private String dateTime;


    @ApiParam(name = "phoneToExamine", value = "电话审核：审核中-1'''',''''审核通过-2'''',''''审核不通过-3")
    private String phoneToExamine;


    @ApiParam(name = "addressToExamine", value = "地址审核：审核中-1'''',''''审核通过-2'''',''''审核不通过-3")
    private String addressToExamine;

    /**
     * 店铺名审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')
     */
    @ApiParam("店铺名审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')")
    private Integer storeNameExamine;


    @ApiParam(name="pageNum",value = "当前页" ,defaultValue = "1")
    private Integer pageNum;


    @ApiParam(name="pageSize",value = "每页显示数量" ,defaultValue = "10")
    private Integer pageSize;


}

package com.hailu.cloud.api.admin.module.merchant.parmeter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

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


    @ApiParam(name = "phoneToExamine", value = "电话审核：审核中-1'''',''''审核通过-2'''',''''审核不通过-3")
    private String phoneToExamine;


    @ApiParam(name = "addressToExamine", value = "地址审核：审核中-1'''',''''审核通过-2'''',''''审核不通过-3")
    private String addressToExamine;

    /**
     * 店铺名审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')
     */
    @ApiModelProperty("店铺名审核表审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')")
    private Integer storeNameExamine;


    @ApiParam(name="pageNum",value = "当前页" ,defaultValue = "1")
    private Integer pageNum;


    @ApiParam(name="pageSize",value = "每页显示数量" ,defaultValue = "10")
    private Integer pageSize;


}

package com.hailu.cloud.api.admin.module.merchant.parmeter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author zhngmugui
 */
@ApiModel
@Data
public class McStoreInformationListParameter {


    /**
     * 商家编号
     */
    @ApiParam(name = "mcNumberId", value = "商家编号")
    private String mcNumberId;


    @ApiParam(name = "shopName", value = "店铺名称")
    private String shopName;


    @ApiParam(name = "phone", value = "店铺电话")
    private String phone;


    @ApiParam(name = "storeSonType", value = "店铺子类型ID")
    private Long storeSonType;


    @ApiParam(name = "storeTotalType", value = "店铺总类型ID")
    private Long storeTotalType;


    @ApiParam(name="pageNum",value = "当前页" ,defaultValue = "1")
    private Integer pageNum;


    @ApiParam(name="pageSize",value = "每页显示数量" ,defaultValue = "10")
    private Integer pageSize;



}

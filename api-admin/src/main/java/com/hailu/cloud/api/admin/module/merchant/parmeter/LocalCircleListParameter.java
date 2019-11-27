package com.hailu.cloud.api.admin.module.merchant.parmeter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author zhngmugui
 */
@ApiModel
@Data
public class LocalCircleListParameter {


    @ApiParam(name = "shopName", value = "店铺名称")
    private String shopName;


    @ApiParam(name = "phone", value = "店铺电话")
    private String phone;


    @ApiParam(name = "firstManagementTypeId", value = "一级经营类型id")
    private Long firstManagementTypeId;


    @ApiParam(name = "secondManagementTypeId", value = "二级经营类型id")
    private Long secondManagementTypeId;


    @ApiParam(name="pageNum",value = "当前页" ,defaultValue = "1")
    private Integer pageNum;


    @ApiParam(name="pageSize",value = "每页显示数量" ,defaultValue = "10")
    private Integer pageSize;


}

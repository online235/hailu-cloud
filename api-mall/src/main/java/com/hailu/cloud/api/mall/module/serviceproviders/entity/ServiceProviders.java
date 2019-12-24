package com.hailu.cloud.api.mall.module.serviceproviders.entity;

import com.hailu.cloud.common.model.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 服务商信息
 */
@Data
public class ServiceProviders extends BaseEntity {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", dataType = "String",required = true)
    private String userId;

    /**
     * 服务商名称
     */
    @ApiModelProperty(value = "服务商名称", dataType = "String",required = true)
    private String name;

    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码", dataType = "String",required = true)
    private String phone;

    /**
     * 省ID
     */
    @ApiModelProperty(value = "省ID", dataType = "Long",required = true)
    private String provinceId;
    /**
     * 市ID
     */
    @ApiModelProperty(value = "市ID", dataType = "Long",required = true)
    private String cityId;
    /**
     * 区县ID
     */
    @ApiModelProperty(value = "区县ID", dataType = "Long",required = true)
    private String areaId;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址", dataType = "String",required = true)
    private String address;

    /**
     * 是否服务商（1-是、2-否）
     */
    private int isService;
    /**
     * 状态（1-正常、2-删除）
     */
    private int status;

}

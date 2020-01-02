package com.hailu.cloud.api.xinan.module.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class Nation implements Serializable {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 地区代码
     */
    @ApiModelProperty("地区代码")
    private String code;

    /**
     * 地区名称
     */
    @ApiModelProperty("地区名称")
    private String areaName;


    /**
     * 父级行政区划ID
     */
    @ApiModelProperty("父级行政区划ID")
    private String parentCode;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;


    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String lng;


    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String lat;


    /**
     * sys_nation
     */
    private static final long serialVersionUID = 1L;
}
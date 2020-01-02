package com.hailu.cloud.api.merchant.module.merchant.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author zhangmugui
 */
@Data
@ApiModel(description = "子类列表")
public class McManagementType implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 经营类型id
     */
    @ApiModelProperty(value = "经营类型id",dataType = "Long")
    private Long managementId;


    /**
     * 父类编号
     */
    @ApiModelProperty(value = "父类编号",dataType = "Long")
    private Long parentId;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号",dataType = "Integer")
    private Integer sort;


    /**
     * 图标颜色
     */
    @ApiModelProperty(value = "图标颜色",dataType = "String")
    private String pictureColour;


    /**
     * 图标链接
     */
    @ApiModelProperty(value = "图标链接",dataType = "String")
    private String pictureCode;


    /**
     * 经营名称
     */
    @ApiModelProperty(value = "经营名称",dataType = "String")
    private String managementName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",dataType = "String")
    private String remarks;


    /**
     * 层级
     */
    @ApiModelProperty("层级")
    private Integer mcLevel;


}

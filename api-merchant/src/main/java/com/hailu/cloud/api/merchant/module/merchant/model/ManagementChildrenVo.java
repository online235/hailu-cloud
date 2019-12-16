package com.hailu.cloud.api.merchant.module.merchant.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangmugui
 */
@Data
@ApiModel(description = "子类列表")
public class ManagementChildrenVo {

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



}

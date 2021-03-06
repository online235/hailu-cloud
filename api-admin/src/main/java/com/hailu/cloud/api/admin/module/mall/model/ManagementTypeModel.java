package com.hailu.cloud.api.admin.module.mall.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@ApiModel
@Data
public class ManagementTypeModel {


    /**
     * 经营类型id
     */
    @ApiParam(name="managementId",value = "经营类型id")
    @ApiModelProperty(value = "经营类型id")
    private Long managementId;

    /**
     * 父类编号
     */
    @ApiParam(name="parentId",value = "父类编号")
    @ApiModelProperty(value = "父类编号")
    private Long parentId;

    /**
     * 图标代码
     */
    @ApiParam(name="pictureCode",value = "图标代码")
    @ApiModelProperty(value = "图标代码")
    private String pictureCode;

    /**
     * 图标颜色
     */
    @ApiParam(name="pictureColour",value = "图标颜色")
    @ApiModelProperty(value = "图标颜色")
    private String pictureColour;

    /**
     * 经营名称
     */
    @ApiParam(name="managementName",value = "经营名称")
    @ApiModelProperty(value = "经营名称")
    private String managementName;

    /**
     * 排序号
     */
    @ApiParam(name="sort",value = "排序号")
    @ApiModelProperty(value = "排序号")
    private Integer sort;

    /**
     * 链接地址
     */
    @ApiParam(name="url",value = "链接地址")
    @ApiModelProperty(value = "链接地址")
    private String url;

    /**
     * 备注
     */
    @ApiParam(name="remarks",value = "备注")
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 层级
     */
    @ApiModelProperty(value = "层级:1、2、3")
    private Integer mcLevel;


    /**
     * 经营项目类型
     */
    @ApiParam(name="managementType",value = "经营项目类型  1   生活圈百货；2  百货")
    @ApiModelProperty(value = "经营项目类型  1   生活圈百货；2  百货")
    private String managementType;


}

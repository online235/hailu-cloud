package com.hailu.cloud.api.mall.module.multiindustry.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data

public class ManagementType implements Serializable {
    /**
     * 经营类型id
     */
    @ApiModelProperty("经营类型id")
    private Long managementId;

    /**
     * 父类编号
     */
    @ApiModelProperty("父类编号")
    private Long parentId;

    /**
     * 图标代码
     */
    @ApiModelProperty("图标代码")
    private String pictureCode;

    /**
     * 图标颜色
     */
    @ApiModelProperty("图标颜色")
    private String pictureColour;

    /**
     * 经营名称
     */
    @ApiModelProperty("经营名称")
    private String managementName;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer sort;

//    /**
//     * 经营项目类型  1 生活圈   ；2  百货
//     */
//    private Integer managementType;

    /**
     * 链接地址
     */
    @ApiModelProperty("链接地址")
    private String url;

    /**
     * mc_management_type
     */
    private static final long serialVersionUID = 1L;

}
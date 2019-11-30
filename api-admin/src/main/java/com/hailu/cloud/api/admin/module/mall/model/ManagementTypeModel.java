package com.hailu.cloud.api.admin.module.mall.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel
public class ManagementTypeModel {
    /**
     * 经营类型id
     */
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

    /**
     * 链接地址
     */
    @ApiModelProperty("链接地址")
    private String url;
}

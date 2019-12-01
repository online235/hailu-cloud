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
    private Long managementId;

    /**
     * 父类编号
     */
    @ApiParam(name="parentId",value = "父类编号")
    private Long parentId;

    /**
     * 图标代码
     */
    @ApiParam(name="pictureCode",value = "图标代码")
    private String pictureCode;

    /**
     * 图标颜色
     */
    @ApiParam(name="pictureColour",value = "图标颜色")
    private String pictureColour;

    /**
     * 经营名称
     */
    @ApiParam(name="managementName",value = "经营名称")
    private String managementName;

    /**
     * 排序号
     */
    @ApiParam(name="sort",value = "排序号")
    private Integer sort;

    /**
     * 链接地址
     */
    @ApiParam(name="url",value = "链接地址")
    private String url;
}

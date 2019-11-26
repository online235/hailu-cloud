package com.hailu.cloud.api.basic.module.mail.model;

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
    @ApiModelProperty("经营类型id")
    private Long managementId;

    /**
     * 父类编号
     */
    @ApiModelProperty("父类编号")
    private Long parentId;

    /**
     * 图片链接
     */
    @ApiModelProperty("图片链接")
    private String pictureUrl;

    /**
     * 经营名称
     */
    @NotBlank(message = "行业名称不能为空")
    @ApiModelProperty("经营名称")
    private String managementName;

    /**
     * 排序号
     */
    @NotNull(message = "排序号不能为空")
    @ApiModelProperty("排序号")
    private Integer sort;
}

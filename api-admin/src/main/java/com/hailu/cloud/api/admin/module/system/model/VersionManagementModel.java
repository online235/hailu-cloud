package com.hailu.cloud.api.admin.module.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 版本管理Model
 * @author 190726
 */
@ToString
@Data
@ApiModel("版本管理Model")
public class VersionManagementModel {

    @ApiModelProperty(value = "id（编辑时传入，新增是无需传）", dataType = "String")
    private Long id;

    /**
     * 版本号
     */
    @NotBlank(message = "版本号不能为空")
    @ApiModelProperty(value = "版本号", dataType = "String",required = true)
    private String version;

    /**
     * 构建号
     */
    @NotBlank(message = "构建号不能为空")
    @ApiModelProperty(value = "构建号", dataType = "String",required = true)
    private String build;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题", dataType = "String",required = true)
    private String title;

    /**
     * 版本内容
     */
    @NotBlank(message = "版本内容不能为空")
    @ApiModelProperty(value = "版本内容", dataType = "String",required = true)
    private String content;

    /**
     * 更新标识
     */
    @NotBlank(message = "更新标识不能为空")
    @ApiModelProperty(value = "更新标识", dataType = "String",required = true)
    private String mandatory;

    /**
     * 1-强制更新、2-非强制更新、3-无需更新
     */
    @NotNull(message = "更新状态不能为空")
    @ApiModelProperty(value = "更新状态", dataType = "Integer",required = true)
    private Integer renewal;

    /**
     * 下载跳转URl
     */
    @ApiModelProperty(value = "更新标识", dataType = "String")
    private String url;


    /**
     * 设备类型（1-IOS、2-安卓）
     */
    @NotNull(message = "设备类型")
    @ApiModelProperty(value = "设备类型", dataType = "Integer",required = true)
    private Integer type;
}

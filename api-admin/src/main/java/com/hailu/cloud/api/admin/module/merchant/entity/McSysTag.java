package com.hailu.cloud.api.admin.module.merchant.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class McSysTag implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 标签组编号
     */
    @ApiModelProperty(value = "标签组编号")
    private Long tagGroupId;

    /**
     * 状态(正常-1、删除-2)
     */
    @ApiModelProperty(value = "状态(正常-1、删除-2)")
    private Integer state;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String tagName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * mc_sys_tag
     */
    private static final long serialVersionUID = 1L;
}
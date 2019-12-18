package com.hailu.cloud.api.merchant.module.merchant.entity;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
@InjectDict
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
     * 状态(启用-1、禁用-2)
     */
    @ApiModelProperty(value = "状态(启用-1、禁用-2)")
    private Integer state;


    @DictName(code = "ENABLE_STATUS", joinField = "state")
    private String stateDisplay;

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
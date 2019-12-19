package com.hailu.cloud.api.admin.module.mall.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

@Data
public class ManagementType implements Serializable {
    /**
     * 经营类型id
     */
    private Long managementId;

    /**
     * 父类编号
     */
    private Long parentId;

    /**
     * 图标代码
     */
    private String pictureCode;

    /**
     * 图标颜色
     */
    private String pictureColour;

    /**
     * 经营名称
     */
    private String managementName;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 经营项目类型
     */
    private String managementType;


    /**
     * mc_management_type
     */
    private static final long serialVersionUID = 1L;

}
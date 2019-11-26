package com.hailu.cloud.api.basic.module.mail.entity;

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
     * 图片链接
     */
    private String pictureUrl;

    /**
     * 经营名称
     */
    private String managementName;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * mc_management_type
     */
    private static final long serialVersionUID = 1L;

}
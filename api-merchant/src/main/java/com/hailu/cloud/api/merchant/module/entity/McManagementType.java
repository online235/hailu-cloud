package com.hailu.cloud.api.merchant.module.entity;


import lombok.Data;

import java.io.Serializable;


@Data
public class McManagementType implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 经营类型id
     */
    private Long managementId;


    /**
     * 父类编号
     */
    private Long parentId;


    /**
     * 经营名称
     */
    private String managementName;


    /**
     * 创建时间
     */
    private Long createdat;


    /**
     * 更新时间
     */
    private Long updatedat;



}

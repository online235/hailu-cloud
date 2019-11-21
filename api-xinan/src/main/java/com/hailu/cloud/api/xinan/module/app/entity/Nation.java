package com.hailu.cloud.api.xinan.module.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Nation implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 地区代码
     */
    private String code;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 父级行政区划ID
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 县/区
     */
    private String district;

    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

    /**
     * 操作人
     */
    private Long operator;

    /**
     * 操作人IP
     */
    private String operatorIp;

    /**
     * sys_nation
     */
    private static final long serialVersionUID = 1L;
}
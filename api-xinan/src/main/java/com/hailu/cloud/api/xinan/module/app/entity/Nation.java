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
     * 地区名称
     */
    private String areaName;


    /**
     * 父级行政区划ID
     */
    private String parentCode;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 经度
     */
    private String lng;


    /**
     * 纬度
     */
    private String lat;


    /**
     * sys_nation
     */
    private static final long serialVersionUID = 1L;
}
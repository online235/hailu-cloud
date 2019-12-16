package com.hailu.cloud.api.merchant.module.merchant.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseEntity {

    /**
     * 创建时间-数据库字段
     */
    private Long createTime;
    /**
     * 修改时间-数据库字段
     */
    private Long updateTime;
    /**
     * 开始时间-数据库字段
     */
    private Long startTime;
    /**
     * 结束时间-数据库字段
     */
    private Long endTime;
    /**
     * 创建时间－页面字段
     */
    private Timestamp createTimeStr;
    private String createTimeSt;
    /**
     * 修改时间－页面字段
     */
    private Timestamp updateTimeStr;
    /**
     * 开始时间－页面字段
     */
    private Timestamp startTimeStr;
    /**
     * 结束时间－页面字段
     */
    private Timestamp endTimeStr;
    /**
     * 0:未删除;1.已删除
     */
    private int isDel;

    private String createName;

    private String updateName;

    /* 删除标记（0：正常；1：删除；2：审核；） */
    /**
     * 正常
     */
    public static final Integer DEL_FLAG_NORMAL = 0;
    /**
     * 删除
     */
    public static final Integer DEL_FLAG_DELETE = 1;
    /**
     * 审核
     */
    public static final Integer DEL_FLAG_AUDIT = 2;

}

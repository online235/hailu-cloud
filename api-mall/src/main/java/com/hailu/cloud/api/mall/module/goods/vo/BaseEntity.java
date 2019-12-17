package com.hailu.cloud.api.mall.module.goods.vo;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
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

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
        if (null != createTime) {
            createTimeStr = new Timestamp(createTime);
            createTimeSt = DateUtil.format(DateUtil.date(createTime), DatePattern.NORM_DATE_FORMAT);
        }
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        if (null != updateTime) {
            updateTimeStr = new Timestamp(updateTime);
        }
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (null != startTime) {
            this.startTimeStr = new Timestamp(startTime);
        }
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (null != endTime) {
            this.endTimeStr = new Timestamp(endTime);
        }
    }

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

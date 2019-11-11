package com.hailu.cloud.api.notify.module.uid.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 全局UID生成主机列表
 *
 * @author yutianbao
 */
@Getter
@Setter
public class GlobalWorkerNodeEntity {

    /**
     * 主键-自增ID
     */
    private long id;

    /**
     * 主机
     */
    private String hostname;

    /**
     * 端口
     */
    private String port;

    /**
     * 节点类型: 1容器, 2物理机
     */
    private int type;

    /**
     * 启动日期
     */
    private Date launchDate = new Date();

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

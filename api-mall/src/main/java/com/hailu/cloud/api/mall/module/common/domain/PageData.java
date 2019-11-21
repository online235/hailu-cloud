package com.hailu.cloud.api.mall.module.common.domain;

import lombok.Data;

/**
 * @Author xuzhijie
 * @Date 2019/10/22 9:31
 */
@Data
public class PageData<T> {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 每页显示数量
     */
    private long pageSize;

    /**
     * 数据
     */
    private T rows;

}

package com.hailu.cloud.api.mall.module.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据
 *
 * @author xuzhijie
 */
@Data
public class DataPage<T> implements Serializable {

    private static final long serialVersionUID = -3305445510010393784L;

    /**
     * 页码
     */
    private int page = 1;

    /**
     * 分页条数
     */
    private int rows = 10;

    /**
     * 分页数据
     */
    private List<T> list = new ArrayList<T>();

    /**
     * 总数
     */
    private int total;

    /**
     * 是否需要查询总数
     */
    private boolean needTotal = true;

    /**
     * 是否需要查询分页数据
     */
    private boolean needList = true;

    /**
     * 分页起始位置
     *
     * @return
     */
    public int getStart() {
        return page <= 1 ? 0 : (page - 1) * rows;
    }
}

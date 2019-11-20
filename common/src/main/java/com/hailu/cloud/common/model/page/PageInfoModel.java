package com.hailu.cloud.common.model.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xuzhijie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoModel<T> {

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 分页数据
     */
    private T datas;

}
package com.hailu.cloud.api.admin.module.xinan.service;


import com.hailu.cloud.api.admin.module.xinan.entity.XaStatistics;
import com.hailu.cloud.api.admin.module.xinan.model.XaStatisticsModel;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

public interface XaStatisticsService {

    /**
     * 查询方法
     *
     * @param parameter
     * @return
     */
    List<XaStatisticsModel> findListByParameter(Object parameter);

    /**
     * 插入方法
     *
     * @param xaStatistics
     * @return
     */
    int insert(XaStatistics xaStatistics);

    /**
     * 更新方法
     *
     * @param xaStatistics
     * @return
     */
    int update(XaStatistics xaStatistics);


    /**
     * 分页操作
     * @param pageNum
     * @param size
     * @param parameter
     * @return
     */
    PageInfoModel<List<XaStatisticsModel>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter);



}

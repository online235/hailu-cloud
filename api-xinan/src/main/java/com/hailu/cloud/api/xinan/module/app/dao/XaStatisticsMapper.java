package com.hailu.cloud.api.xinan.module.app.dao;



import com.hailu.cloud.api.xinan.module.app.entity.XaStatistics;
import com.hailu.cloud.api.xinan.module.app.model.XaStatisticsModel;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface XaStatisticsMapper {

    /**
     * 查询方法
     *
     * @param parameter
     * @return
     */
    List<XaStatisticsModel> findListByParameter(Object parameter);



//    /**
//     * 插入方法
//     *
//     * @param xaStatistics
//     * @return
//     */
//    int insert(XaStatistics xaStatistics);
//
//    /**
//     * 更新方法
//     *
//     * @param xaStatistics
//     * @return
//     */
//    int update(XaStatistics xaStatistics);

}

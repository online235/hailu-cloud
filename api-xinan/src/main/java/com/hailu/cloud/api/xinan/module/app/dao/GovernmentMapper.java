package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.model.GovernmentModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/3 0003 15:05
 */
@Mapper
public interface GovernmentMapper {

    /**
     * 根据城市查询文章
     * @param cityCode
     * @return
     */
    GovernmentModel findGovernmentUsersByCityCode(String cityCode);
}
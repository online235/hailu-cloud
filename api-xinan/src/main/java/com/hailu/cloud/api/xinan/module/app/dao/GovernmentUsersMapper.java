package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.GovernmentUsers;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/3 0003 15:05
 */
@Mapper
public interface GovernmentUsersMapper {
    /**
     *
     * @mbggenerated 2019-12-02
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int insert(GovernmentUsers record);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int insertSelective(GovernmentUsers record);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    GovernmentUsers selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKeySelective(GovernmentUsers record);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKeyWithBLOBs(GovernmentUsers record);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKey(GovernmentUsers record);

    /**
     * 根据城市查询文章
     * @param cityCode
     * @return
     */
    GovernmentUsers findGovernmentUsersByCityCode(String cityCode);
}
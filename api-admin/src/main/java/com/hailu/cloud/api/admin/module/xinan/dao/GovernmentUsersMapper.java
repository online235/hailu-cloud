package com.hailu.cloud.api.admin.module.xinan.dao;

import com.hailu.cloud.api.admin.module.xinan.entity.GovernmentUsers;
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
     * 添加政府账号
     * @mbggenerated 2019-12-02
     */
    int insertSelective(GovernmentUsers record);

    /**
     * 根据编号查询账号详细信息
     * @mbggenerated 2019-12-02
     */
    GovernmentUsers selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKeySelective(GovernmentUsers record);

    /**
     * 根据城市查询文章
     * @param cityCode
     * @return
     */
    GovernmentUsers findGovernmentUsersByCityCode(String cityCode);
}
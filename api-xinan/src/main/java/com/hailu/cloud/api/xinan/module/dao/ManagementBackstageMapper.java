package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.ManagementBackstage;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagementBackstageMapper {
    /**
     *
     * @mbggenerated 2019-11-15
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    int insert(ManagementBackstage record);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    int insertSelective(ManagementBackstage record);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    ManagementBackstage selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    int updateByPrimaryKeySelective(ManagementBackstage record);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    int updateByPrimaryKey(ManagementBackstage record);

    int findLogin(@Param("userName") String userName, @Param("passWord") String passWord);
}
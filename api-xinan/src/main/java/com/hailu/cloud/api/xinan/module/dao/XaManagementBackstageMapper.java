package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.XaManagementBackstage;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XaManagementBackstageMapper {
    /**
     *
     * @mbggenerated 2019-11-15
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    int insert(XaManagementBackstage record);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    int insertSelective(XaManagementBackstage record);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    XaManagementBackstage selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    int updateByPrimaryKeySelective(XaManagementBackstage record);

    /**
     *
     * @mbggenerated 2019-11-15
     */
    int updateByPrimaryKey(XaManagementBackstage record);

    int findLogin(@Param("userName") String userName, @Param("passWord") String passWord);
}
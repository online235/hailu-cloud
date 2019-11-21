package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.Mutualaid;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表Mapper
 * @Date: 18:13 2019/11/12 0012
 */
@Mapper
public interface MutualaidMapper {
    /**
     *
     * @mbggenerated 2019-11-12
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insert(Mutualaid record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(Mutualaid record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    Mutualaid selectByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(Mutualaid record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeyWithBLOBs(Mutualaid record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKey(Mutualaid record);
}
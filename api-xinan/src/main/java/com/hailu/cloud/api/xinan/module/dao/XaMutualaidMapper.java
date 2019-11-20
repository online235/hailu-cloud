package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.XaMutualaid;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表Mapper
 * @Date: 18:13 2019/11/12 0012
 */
@Mapper
public interface XaMutualaidMapper {
    /**
     *
     * @mbggenerated 2019-11-12
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insert(XaMutualaid record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(XaMutualaid record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    XaMutualaid selectByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(XaMutualaid record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeyWithBLOBs(XaMutualaid record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKey(XaMutualaid record);
}
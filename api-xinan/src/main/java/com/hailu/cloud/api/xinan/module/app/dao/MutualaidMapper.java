package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.MutualAid;
import com.hailu.cloud.api.xinan.module.app.model.MutualAidModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    int insertSelective(MutualAid record);

    /**
     * 互助详细信息
     * @mbggenerated 2019-11-12
     */
    MutualAid findMutualAid(@Param("numberId") Long numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(MutualAid record);

    /**
     * 查询互助列表
     * @param rescueType
     * @return
     */
    List<MutualAidModel> findMutualaidList(@Param("rescueType") Integer rescueType);
}
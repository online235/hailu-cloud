package com.hailu.cloud.api.admin.module.xinan.dao;

import com.hailu.cloud.api.admin.module.xinan.entity.MutualAid;
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
    int deleteByPrimaryKey(Long numberId);

    /**
     * 互助详细信息
     * @mbggenerated 2019-11-12
     */
    MutualAid findMutualAid(@Param("numberId") Long numberId);

    /**
     * 查询互助列表
     * @param rescueType
     * @return
     */
    List<MutualAid> findMutualaidList(@Param("rescueType") Integer rescueType,@Param("diseaseName") String diseaseName);

    /**
     * 更改审核状态
     * @param toExamine
     * @param numberId
     * @return
     */
    int updateMutualAidOfExamine(@Param("toExamine") Integer toExamine, @Param("numberId") Long numberId);
}
package com.hailu.cloud.api.admin.module.xinan.dao;

import com.hailu.cloud.api.admin.module.xinan.entity.Helppictures;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表图片Mapper
 * @Date: 18:13 2019/11/12 0012
 */
@Mapper
public interface HelppicturesMapper {
    /**
     *
     * @mbggenerated 2019-11-12
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(Helppictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    Helppictures selectByPrimaryKey(@Param("numberId") Long numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(Helppictures record);

    /**
     * 查询互助者图片
     * @return
     */
    List<Helppictures> findHelppicturesList(@Param("mutualaId") Long mutualaId);


    /**
     * 批量删除相册
     * @param mutualaId
     * @return
     */
    int deleteHelppictures(@Param("mutualaId") Long mutualaId);


}
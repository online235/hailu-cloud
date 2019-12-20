package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.Helppictures;
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
    List<Helppictures> findHelppicturesList(@Param("numberId") Long numberId);


    /**
     * 根据参数查列表
     * @param parameter
     * @return
     */
    List<Helppictures> findListByParameter(Object parameter);




}
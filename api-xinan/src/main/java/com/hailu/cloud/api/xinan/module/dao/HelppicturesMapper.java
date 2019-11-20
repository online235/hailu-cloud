package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.Helppictures;
import org.apache.ibatis.annotations.Mapper;

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
    int insert(Helppictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(Helppictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    Helppictures selectByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(Helppictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKey(Helppictures record);
}
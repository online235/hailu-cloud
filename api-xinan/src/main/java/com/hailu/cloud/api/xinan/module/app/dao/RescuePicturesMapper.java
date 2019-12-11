package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.RescuePictures;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表图片Mapper
 * @Date: 18:14 2019/11/12 0012
 */
@Mapper
public interface RescuePicturesMapper {
    /**
     *
     * @mbggenerated 2019-11-12
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(RescuePictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    RescuePictures selectByPrimaryKey(String numberId);

    /**
     *
     * @param MutualAid
     * @return
     */
    List<RescuePictures> selectByPrimaryMutualAid(String MutualAid);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(RescuePictures record);
}
package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.XaRescuePictures;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表图片Mapper
 * @Date: 18:14 2019/11/12 0012
 */
@Mapper
public interface XaRescuePicturesMapper {
    /**
     *
     * @mbggenerated 2019-11-12
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insert(XaRescuePictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(XaRescuePictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    XaRescuePictures selectByPrimaryKey(String numberId);

    /**
     *
     * @param MutualAid
     * @return
     */
    List<XaRescuePictures> selectByPrimaryMutualAid(String MutualAid);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(XaRescuePictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKey(XaRescuePictures record);
}
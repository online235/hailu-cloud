package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.XaHelppictures;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表图片Mapper
 * @Date: 18:13 2019/11/12 0012
 */
@Mapper
public interface XaHelppicturesMapper {
    /**
     *
     * @mbggenerated 2019-11-12
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insert(XaHelppictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(XaHelppictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    XaHelppictures selectByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(XaHelppictures record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKey(XaHelppictures record);
}
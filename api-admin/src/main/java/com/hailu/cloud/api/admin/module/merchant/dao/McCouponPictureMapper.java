package com.hailu.cloud.api.admin.module.merchant.dao;

import com.hailu.cloud.api.admin.module.merchant.entity.McCouponPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/20 0020 10:29
 */
@Mapper
public interface McCouponPictureMapper {

    /**
     * 删除图片
     * @mbggenerated 2019-12-20
     */
    int deleteByPrimaryKey(@Param("id") Long id, @Param("couponId") Long couponId);

    /**
     * 删除图片
     * @mbggenerated 2019-12-20
     */
    int delPictureByCouponId( @Param("couponId") Long couponId);

    /**
     * 更改图片状态
     * @mbggenerated 2019-12-20
     */
    int updMcCouponPictureById(@Param("id") Long id, @Param("couponId") Long couponId);

    /**
     * 查询单张图片
     * @mbggenerated 2019-12-20
     */
    McCouponPicture selectByPrimaryKey(Long id);

    /**
     * 查询图片
     * @param couponId
     * @return
     */
    List<McCouponPicture> findMcCouponPictureListByCouponId(@Param("couponId") Long couponId);
}
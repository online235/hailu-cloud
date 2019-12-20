package com.hailu.cloud.api.merchant.module.merchant.dao;

import com.hailu.cloud.api.merchant.module.merchant.entity.McCouponPicture;
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
     * 循环添加到店卷图片
     * @mbggenerated 2019-12-20
     */
    int insertList(List<McCouponPicture> record);

    /**
     * 添加到店卷图片
     * @mbggenerated 2019-12-20
     */
    int insertMcCouponPicture(McCouponPicture record);

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
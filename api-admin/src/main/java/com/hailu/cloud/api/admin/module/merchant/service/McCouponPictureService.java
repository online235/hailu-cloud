package com.hailu.cloud.api.admin.module.merchant.service;

import com.hailu.cloud.api.admin.module.merchant.entity.McCouponPicture;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 到店卷图片
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 10:
 */
public interface McCouponPictureService {

    /**
     * 查询图片
     * @param couponId
     * @return
     */
    List<McCouponPicture> findMcCouponPictureListByCouponId(Long couponId);

    /**
     * 删除图片
     * @mbggenerated 2019-12-20
     */
    void deleteByPrimaryKey(Long id, Long couponId);

    /**
     * 删除到店卷下所有图片
     * @mbggenerated 2019-12-20
     */
    void delPictureByCouponId(Long couponId);
}

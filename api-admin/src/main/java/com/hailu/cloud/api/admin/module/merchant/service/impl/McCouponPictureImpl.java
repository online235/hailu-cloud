package com.hailu.cloud.api.admin.module.merchant.service.impl;

import com.hailu.cloud.api.admin.module.merchant.dao.McCouponPictureMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.McCouponPicture;
import com.hailu.cloud.api.admin.module.merchant.service.McCouponPictureService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 10:
 */
@Service
public class McCouponPictureImpl implements McCouponPictureService {

    @Resource
    private McCouponPictureMapper mcCouponPictureMapper;

    @Override
    public List<McCouponPicture> findMcCouponPictureListByCouponId(Long couponId) {
        return mcCouponPictureMapper.findMcCouponPictureListByCouponId(couponId);
    }

    @Override
    public void deleteByPrimaryKey(Long id, Long couponId) {
        mcCouponPictureMapper.updMcCouponPictureById(id,couponId);
    }

    @Override
    public void delPictureByCouponId(Long couponId) {
        mcCouponPictureMapper.delPictureByCouponId(couponId);
    }
}

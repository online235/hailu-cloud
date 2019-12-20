package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.McCouponPictureMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McCouponPicture;
import com.hailu.cloud.api.merchant.module.merchant.service.McCouponPictureService;
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

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public List<McCouponPicture> insert(String[] picturePath, Integer[] pictureType, Long couponId) {
        List<McCouponPicture> mcCouponPictureList = new ArrayList<>();
        for (int i = 0; i >= picturePath.length; i++){
            McCouponPicture mcCouponPicture = new McCouponPicture();
            mcCouponPicture.setId(basicFeignClient.uuid().getData());
            mcCouponPicture.setCouponId(couponId);
            mcCouponPicture.setDateTime(new Date());
            mcCouponPicture.setPicturePath(picturePath[i]);
            mcCouponPicture.setPictureType(pictureType[i]);
            mcCouponPicture.setState(1);
            mcCouponPictureList.add(mcCouponPicture);
        }
        mcCouponPictureMapper.insertList(mcCouponPictureList);
        return mcCouponPictureList;
    }

    @Override
    public McCouponPicture insertMcCouponPicture(String picturePath, Integer pictureType, Long couponId) {
        McCouponPicture mcCouponPicture = new McCouponPicture();
        mcCouponPicture.setId(basicFeignClient.uuid().getData());
        mcCouponPicture.setCouponId(couponId);
        mcCouponPicture.setDateTime(new Date());
        mcCouponPicture.setPicturePath(picturePath);
        mcCouponPicture.setPictureType(pictureType);
        mcCouponPicture.setState(1);
        mcCouponPictureMapper.insertMcCouponPicture(mcCouponPicture);
        return mcCouponPicture;
    }

    @Override
    public List<McCouponPicture> findMcCouponPictureListByCouponId(Long couponId) {
        return mcCouponPictureMapper.findMcCouponPictureListByCouponId(couponId);
    }

    @Override
    public void deleteByPrimaryKey(Long id, Long couponId) {
        mcCouponPictureMapper.updMcCouponPictureById(id,couponId);
    }
}

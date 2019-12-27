package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.McCouponMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McCoupon;
import com.hailu.cloud.api.mall.module.multiindustry.model.CouponAndPictureModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.McCouponService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 10:
 */
@Service
public class McCouponImpl implements McCouponService {

    @Resource
    private McCouponMapper mcCouponMapper;


    @Override
    public McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(Long id, Long numberId) {
        McCoupon<McCouponOtherJsonModel> mcCoupon = mcCouponMapper.selectByPrimaryKey(id, numberId);
        //json转为类格式
        McCouponOtherJsonModel mcCouponOtherJsonModel = JSON.parseObject(mcCoupon.getOtherJson(), McCouponOtherJsonModel.class);
        //赋值
        mcCoupon.setMcCouponOtherJsonModel(mcCouponOtherJsonModel);
        mcCoupon.setOtherJson(null);
        return mcCoupon;
    }


    @Override
    public PageInfoModel<List<CouponAndPictureModel<McCouponOtherJsonModel>>> findMcCouponList(Long mcNumberId, Integer page, Integer size) {
        Page pageList = PageHelper.startPage(page, size);
        List<CouponAndPictureModel<McCouponOtherJsonModel>> mcCouponsList = mcCouponMapper.findMcCouponList(mcNumberId);

        //创建一个新的集合
        List<CouponAndPictureModel<McCouponOtherJsonModel>> CouponOtherJsonModel = new ArrayList<>();

        //遍历mcCouponsList
        for (CouponAndPictureModel<McCouponOtherJsonModel> couponAndPictureModel : mcCouponsList){
            //json转成实体
            McCouponOtherJsonModel mcCouponOtherJsonModel = JSON.parseObject(couponAndPictureModel.getOtherJson(), McCouponOtherJsonModel.class);
            couponAndPictureModel.setMcCouponOtherJsonModel(mcCouponOtherJsonModel);
            CouponOtherJsonModel.add(couponAndPictureModel);
        }
        return new PageInfoModel<>(pageList.getPages(), pageList.getTotal(), CouponOtherJsonModel);
    }

    @Override
    public int findMcCouponById(Long id) {
        return mcCouponMapper.findMcCouponById(id);
    }

}

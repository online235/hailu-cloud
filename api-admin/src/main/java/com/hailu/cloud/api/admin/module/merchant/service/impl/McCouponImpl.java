package com.hailu.cloud.api.admin.module.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.merchant.dao.McCouponMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.McCoupon;
import com.hailu.cloud.api.admin.module.merchant.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.admin.module.merchant.service.McCouponPictureService;
import com.hailu.cloud.api.admin.module.merchant.service.McCouponService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Autowired
    private McCouponPictureService mcCouponPictureService;

    @Override
    public McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(Long id) {
        McCoupon<McCouponOtherJsonModel> mcCoupon = mcCouponMapper.selectByPrimaryKey(id);
        //json转为类格式
        McCouponOtherJsonModel mcCouponOtherJsonModel = mcCoupon.getOtherJson() == null ? null : JSON.parseObject(mcCoupon.getOtherJson(), McCouponOtherJsonModel.class);
        //赋值
        mcCoupon.setMcCouponOtherJsonModel(mcCouponOtherJsonModel);
        mcCoupon.setOtherJson(null);
        return mcCoupon;
    }

    @Override
    public PageInfoModel<List<McCoupon>> findMcCouponList(
            Long storeTotalType, String volumeName,
            Integer toExamine,
            Integer shelfState,
            Integer page,
            Integer size) {
        Page pageList = PageHelper.startPage(page, size);
        List<McCoupon> mcCouponsList = mcCouponMapper.findMcCouponList(storeTotalType,volumeName,toExamine,shelfState);
        return new PageInfoModel<>(pageList.getPages(), pageList.getTotal(), mcCouponsList);
    }

    @Override
    public void updMcCouponById(Long id, Integer toExamine) {
        int state = 0;
        if (toExamine == 2){
            McCoupon<McCouponOtherJsonModel> mcCouponOtherJsonModelMcCoupon = selectByPrimaryKey(id);
            state = mcCouponOtherJsonModelMcCoupon.getMcCouponOtherJsonModel() != null &&
                    mcCouponOtherJsonModelMcCoupon.getMcCouponOtherJsonModel().getImmediatelyMounted() == 1 ? 2 : 1;
        }else if (toExamine == 1 || toExamine == 3){
            state = 1;
        }
        mcCouponMapper.updMcCouponById(id,toExamine,state);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mcCouponMapper.deleteByPrimaryKey(id);
        mcCouponPictureService.delPictureByCouponId(id);
    }


}

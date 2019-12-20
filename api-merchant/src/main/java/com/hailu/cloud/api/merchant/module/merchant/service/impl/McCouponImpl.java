package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.merchant.module.merchant.dao.McCouponMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McCoupon;
import com.hailu.cloud.api.merchant.module.merchant.model.McCouponModel;
import com.hailu.cloud.api.merchant.module.merchant.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McCouponPictureService;
import com.hailu.cloud.api.merchant.module.merchant.service.McCouponService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class McCouponImpl implements McCouponService {

    @Resource
    private McCouponMapper mcCouponMapper;

    @Autowired
    private McCouponPictureService mcCouponPictureService;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public McCoupon insertSelective(McCouponModel record, McCouponOtherJsonModel mcCouponOtherJsonModel, String[] picturePath, Integer[] pictureType, Integer submissionType) {
        McCoupon mcCoupon = new McCoupon();
        //转换类型
        BeanUtils.copyProperties(record, mcCoupon);
        return addOrModify(mcCoupon, mcCouponOtherJsonModel, picturePath, pictureType,submissionType);
    }

    @Override
    public McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(Long id) {
        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        McCoupon<McCouponOtherJsonModel> mcCoupon = mcCouponMapper.selectByPrimaryKey(id,Long.parseLong(merchantUserLoginInfoModel.getNumberid()));
        //json转为类格式
        McCouponOtherJsonModel mcCouponOtherJsonModel = JSON.parseObject(mcCoupon.getOtherJson(), McCouponOtherJsonModel.class);
        //赋值
        mcCoupon.setMcCouponOtherJsonModel(mcCouponOtherJsonModel);
        mcCoupon.setOtherJson(null);
        return mcCoupon;
    }

    @Override
    public McCoupon updateByPrimaryKeySelective(McCoupon record, McCouponOtherJsonModel mcCouponOtherJsonModel) {
        return addOrModify(record, mcCouponOtherJsonModel,null,null,null);
    }

    @Override
    public PageInfoModel<List<McCoupon>> findMcCouponList(String volumeName, Integer toExamine, Integer shelfState, Integer page, Integer size) {
        Page pageList = PageHelper.startPage(page, size);
        List<McCoupon> mcCouponsList = mcCouponMapper.findMcCouponList(volumeName,toExamine,shelfState);
        return new PageInfoModel<>(pageList.getPages(), pageList.getTotal(), mcCouponsList);
    }

    @Override
    public void updShelfStateById(Long id, Integer shelfState) {
            mcCouponMapper.updShelfStateById(id,shelfState);

    }

    public McCoupon addOrModify(McCoupon record, McCouponOtherJsonModel mcCouponOtherJsonModel, String[] picturePath, Integer[] pictureType,Integer submissionType){
        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        Date date = new Date();
        record.setUpdateDateTime(date);
        if (record.getId() == null){
            Long numberId = basicFeignClient.uuid().getData();
            record.setId(numberId);
            record.setNumberId(Long.parseLong(merchantUserLoginInfoModel.getNumberid()));
            record.setDateTime(date);

            if (submissionType == 2){
                record.setToExamine(1);
                record.setShelfState(1);
            }else if(submissionType == 1){
                record.setToExamine(4);
            }

            record.setOtherJson(JSON.toJSONString(mcCouponOtherJsonModel));
            mcCouponPictureService.insert(picturePath,pictureType,numberId);
            //类转为json
            mcCouponMapper.insertSelective(record);
            return record;
        }

        if (submissionType == 2){
            record.setToExamine(1);
        }else if(submissionType == 1){
            record.setToExamine(4);
        }
        record.setShelfState(1);
        record.setOtherJson(JSON.toJSONString(mcCouponOtherJsonModel));
        record.setNumberId(Long.parseLong(merchantUserLoginInfoModel.getNumberid()));
        mcCouponMapper.updateByPrimaryKeySelective(record);
        return record;
    }
}

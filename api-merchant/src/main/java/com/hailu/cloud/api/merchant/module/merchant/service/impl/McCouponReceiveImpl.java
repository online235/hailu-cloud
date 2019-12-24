package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.merchant.module.merchant.dao.McCouponReceiveMapper;
import com.hailu.cloud.api.merchant.module.merchant.model.CouponAndReceiveModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McCouponReceiveService;
import com.hailu.cloud.api.merchant.module.merchant.service.McCouponService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import com.hailu.cloud.common.utils.StoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/23 0023
 * @program: cloud
 * @create: 2019-12-23 11:
 */
@Service
public class McCouponReceiveImpl implements McCouponReceiveService {

    @Resource
    private McCouponReceiveMapper mcCouponReceiveMapper;

    @Override
    public int updUsageStateById(Long id, Long usageState) {
        return mcCouponReceiveMapper.updUsageStateById(id, usageState);
    }

    @Override
    public PageInfoModel<List<CouponAndReceiveModel>> findMcCouponByMemberIdList(Integer usageState, Integer page, Integer size) {
        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        Page pageNum = PageHelper.startPage(page,size);
        List<CouponAndReceiveModel> modelList = mcCouponReceiveMapper.findMcCouponByMemberIdList(merchantUserLoginInfoModel.getNumberid(),usageState);
        List<CouponAndReceiveModel> couponAndReceiveModels = new ArrayList<>();

        for (CouponAndReceiveModel model : modelList){
            if (model.getValidPeriodType() == 1){
                //添加设定天数
                model.setEndTime(StoreUtil.datePlus(model.getDateTime(),model.getTimeAfterOrder()));
                model.setStartTime(model.getDateTime());
            }

            if (model.getUsageState() != 3) {
                //判断时间是否过期
                int result = StoreUtil.dateCompare(model.getStartTime(), new Date(), 1);
                if (result != -1) {
                    //改变状态
                    updUsageStateById(model.getReceiveId(), 3L);
                    model.setUsageState(3L);
                    continue;
                }
            }
            couponAndReceiveModels.add(model);
        }

        return  new PageInfoModel<>(pageNum.getPages(), pageNum.getTotal(), couponAndReceiveModels);
    }

    @Override
    public void updUsageStateByAwardCodeAndStoreId(String awardCode) throws BusinessException {
        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        int result = mcCouponReceiveMapper.updUsageStateByAwardCodeAndStoreId(awardCode,merchantUserLoginInfoModel.getNumberid());
        if (result == 0 || result < 0){
            throw new BusinessException("请检查是否正确");
        }
    }

}

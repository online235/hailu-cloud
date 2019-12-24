package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.merchant.module.merchant.dao.MultiIndustryOrderMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreFunction;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.entity.MultiIndustryOrder;
import com.hailu.cloud.api.merchant.module.merchant.model.McStoreFunctionModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreFunctionService;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreInformationService;
import com.hailu.cloud.api.merchant.module.merchant.service.MultiIndustryOrderService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.StoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MultiIndustryOrderImpl implements MultiIndustryOrderService {

    @Resource
    private MultiIndustryOrderMapper multiIndustryOrderMapper;

    @Autowired
    private McStoreInformationService mcStoreInformationService;

    @Autowired
    private McStoreFunctionService mcStoreFunctionService;

    @Override
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByStoreId( Integer state,  Integer page , Integer size) {
        McStoreInformation mcStoreInformation = mcStoreInformationService.findMcStoreInformation();

        Integer secondState = null;
        if (state == 1){
            secondState = 4;
        }

        Page pageData = PageHelper.startPage(page,size);
        List<MultiIndustryOrder> order = multiIndustryOrderMapper.findOrderListByStoreId(mcStoreInformation.getId(), state, secondState);
        List<MultiIndustryOrder> orders = new ArrayList<>();

        //判断失效时间
        Date date = new Date();
        Date dateSave = new Date();
        for (MultiIndustryOrder mo : order){

            McStoreFunction mcStoreFunction = mcStoreFunctionService.findObjectByStoreId(mo.getStoreId());
            //判断使用日期年月日
            int resultDate = StoreUtil.dateCompare(mo.getUseDate(), dateSave, 1);

            //在原时间加上保留时间
            dateSave.setTime(mo.getOrderTime().getTime() + mcStoreFunction.getAppointmentSaveTime()*60*1000);

            //判断使用时间时分秒
            int resultTime = StoreUtil.dateCompare(mo.getUseTime(), dateSave, 2);
            if (resultDate != -1){
                if (resultTime != -1){
                    updateOrderState(mo.getId(), 3);
                    mo.setState(3);
                }
            }

            if (mo.getState() == 1){
                //在原时间加上失效时间
                date.setTime(mo.getOrderTime().getTime() + 30*60*1000);
                int result = DateUtil.compare(new Date(), date);
                if (result == 0 || result > 0) {
                    updateOrderState(mo.getId(), 4);
                    mo.setState(4);
                }
            }
            orders.add(mo);
        }

        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }

    @Override
    public void updateOrderState(Long id, Integer state) {
        multiIndustryOrderMapper.updateOrderState(id, state);
    }
}

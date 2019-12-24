package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.merchant.module.merchant.dao.MultiIndustryOrderMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.entity.MultiIndustryOrder;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreInformationService;
import com.hailu.cloud.api.merchant.module.merchant.service.MultiIndustryOrderService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MultiIndustryOrderImpl implements MultiIndustryOrderService {

    @Resource
    private MultiIndustryOrderMapper multiIndustryOrderMapper;

    @Autowired
    private McStoreInformationService mcStoreInformationService;

    @Override
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByStoreId( Integer page , Integer size) {
        McStoreInformation mcStoreInformation = mcStoreInformationService.findMcStoreInformation();
        Page pageData = PageHelper.startPage(page,size);
        List<MultiIndustryOrder> orders = multiIndustryOrderMapper.findOrderListByStoreId(mcStoreInformation.getId());
        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }

    @Override
    public void updateOrderState(Long id) {
        multiIndustryOrderMapper.updateOrderState(id);
    }
}

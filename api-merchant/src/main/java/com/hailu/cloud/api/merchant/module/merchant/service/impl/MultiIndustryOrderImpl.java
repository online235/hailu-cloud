package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.merchant.module.merchant.dao.MultiIndustryOrderMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.MultiIndustryOrder;
import com.hailu.cloud.api.merchant.module.merchant.service.MultiIndustryOrderService;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MultiIndustryOrderImpl implements MultiIndustryOrderService {

    @Resource
    private MultiIndustryOrderMapper multiIndustryOrderMapper;

    @Override
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByStoreId(HttpServletRequest request, Integer page , Integer size) {
        MerchantUserLoginInfoModel loginInfo = RequestUtils.getMerchantUserLoginInfo();
        Page pageData = PageHelper.startPage(page,size);
        List<MultiIndustryOrder> orders = multiIndustryOrderMapper.findOrderListByStoreId(Long.parseLong(loginInfo.getNumberid()));
        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }
}

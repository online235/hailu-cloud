package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.MultiIndustryOrderMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.service.MultiIndustryOrderService;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class MultiIndustryOrderImpl implements MultiIndustryOrderService {

    @Resource
    private MultiIndustryOrderMapper multiIndustryOrderMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Autowired
    private StoreInformationService storeInformationService;

    @Override
    public void insertSelective(MultiIndustryOrder record, HttpServletRequest request) {

        StoreInformation storeInformation = storeInformationService.findStoreInformation(record.getStoreId());
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        String num = String.valueOf(basicFeignClient.uuid());
        record.setMemberId(loginInfo.getUserId());
        record.setId(Long.parseLong(num));
        record.setOrderTime(new Date());
        record.setOrderNumber(IdUtil.simpleUUID());
        record.setExchangeCode(RandomUtil.randomNumbers(10));
        record.setProductTitle(storeInformation.getShopName());
        record.setState(1);
        multiIndustryOrderMapper.insertSelective(record);
    }

    @Override
    public MultiIndustryOrder selectByPrimaryKey(Integer id) {
        return multiIndustryOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByMemberId(HttpServletRequest request , Integer page , Integer size) {
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        Page pageData = PageHelper.startPage(page,size);
        List<MultiIndustryOrder> orders = multiIndustryOrderMapper.findOrderListByMemberId(loginInfo.getUserId());
        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }

    @Override
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByStoreId(HttpServletRequest request, Integer page , Integer size) {
        MerchantUserLoginInfoModel loginInfo = (MerchantUserLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        Page pageData = PageHelper.startPage(page,size);
        List<MultiIndustryOrder> orders = multiIndustryOrderMapper.findOrderListByStoreId(Long.parseLong(loginInfo.getNumberid()));
        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }
}

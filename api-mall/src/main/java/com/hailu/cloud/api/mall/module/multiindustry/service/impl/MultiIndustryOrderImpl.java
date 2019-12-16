package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.MultiIndustryOrderMapper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.model.McOrderModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.MultiIndustryOrderService;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MultiIndustryOrderImpl implements MultiIndustryOrderService {

    @Resource
    private MultiIndustryOrderMapper multiIndustryOrderMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Resource
    private StoreInformationMapper storeInformationMapper;

    @Override
    public McOrderModel insertSelective(MultiIndustryOrder record, HttpServletRequest request) throws BusinessException, ParseException {

        StoreInformation storeInformation = storeInformationMapper.findStoreInformation(record.getStoreId());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        String num = String.valueOf(basicFeignClient.uuid().getData());
        record.setMemberId(loginInfo.getUserId());
        record.setTotalType(storeInformation.getStoreTotalType());
        record.setId(Long.parseLong(num));
        record.setOrderTime(new Date());
        record.setOrderNumber(basicFeignClient.uuid().getData() + sdf.format(new Date()) + (System.currentTimeMillis() / 1000));
        record.setExchangeCode(RandomUtil.randomNumbers(10));
        record.setProductTitle(storeInformation.getShopName());
        record.setState(1);
        record.setPurchaseQuantity(1L);
        multiIndustryOrderMapper.insertSelective(record);
        return selectDefaultHead(Long.parseLong(num));
    }

    @Override
    public McOrderModel selectDefaultHead(Long id) {
        return multiIndustryOrderMapper.selectDefaultHead(id);
    }

    @Override
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByMemberId(Integer page , Integer size,Integer state) {
        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        Page pageData = PageHelper.startPage(page,size);
        List<MultiIndustryOrder> orders = multiIndustryOrderMapper.findOrderListByMemberId(loginInfo.getUserId(),state);
        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }
}

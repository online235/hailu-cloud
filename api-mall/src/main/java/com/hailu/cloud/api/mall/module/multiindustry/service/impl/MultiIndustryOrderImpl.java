package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.MultiIndustryOrderMapper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.model.McOrderModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.MultiIndustryOrderService;
import com.hailu.cloud.common.enums.OrderNumberEnum;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.OrderNumberGenerator;
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
    public McOrderModel insertSelective(MultiIndustryOrder record){

        StoreInformation storeInformation = storeInformationMapper.findStoreInformation(record.getStoreId());

        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        Long num = basicFeignClient.uuid().getData();
        //用户编号
        record.setMemberId(loginInfo.getUserId());
        //多行业类型
        record.setTotalType(storeInformation.getStoreTotalType());
        //编号
        record.setId(num);
        //下单时间
        record.setOrderTime(new Date());
        //订单号
        record.setOrderNumber(OrderNumberGenerator.create(OrderNumberEnum.MS_JOIN_MEMBER, String.valueOf(num)));
        //订单类型
        record.setOrderType(OrderNumberEnum.MS_JOIN_MEMBER.getSystem());
        //兑换码
        record.setExchangeCode(RandomUtil.randomNumbers(10));
        //产品标题
        record.setProductTitle(storeInformation.getShopName());
        //订单状态(未完成-1、已完成-2)
        record.setState(1);
        //购买数量（默认1，扩展预留）
        record.setPurchaseQuantity(1L);
        multiIndustryOrderMapper.insertSelective(record);
        return selectDefaultHead(num);
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

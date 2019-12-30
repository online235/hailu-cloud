package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.feigns.MerChantFeignClient;
import com.hailu.cloud.api.mall.module.multiindustry.dao.MultiIndustryOrderMapper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.model.McOrderModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McStoreFunctionModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.MultiIndustryOrderService;
import com.hailu.cloud.common.enums.OrderNumberEnum;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.OrderNumberGenerator;
import com.hailu.cloud.common.utils.RequestUtils;
import com.hailu.cloud.common.utils.StoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private MerChantFeignClient merChantFeignClient;

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
        //订单状态(待确认-1、已确认-2、已完成-3、 预定失败-4、已取消-5)
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
    public PageInfoModel<List<MultiIndustryOrder>> findOrderListByMemberId(Integer page , Integer size,Integer state){
        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        Integer secondState = null;
        if (state == 1){
            secondState = 4;
        }

        Page pageData = PageHelper.startPage(page,size);
        List<MultiIndustryOrder> order = multiIndustryOrderMapper.findOrderListByMemberId(loginInfo.getUserId(),state,secondState);
        List<MultiIndustryOrder> orders = new ArrayList<>();

        //判断失效时间
        Date date = new Date();
        Date dateSave = new Date();
        for (MultiIndustryOrder mo : order){

            McStoreFunctionModel mcStoreFunctionModel = merChantFeignClient.getStoreFunctionDetail(mo.getStoreId()).getData();
            //判断使用日期年月日
            int resultDate = StoreUtil.dateCompare(mo.getUseDate(), dateSave, 1);

            //在原时间加上保留时间
            dateSave.setTime(mo.getOrderTime().getTime() + mcStoreFunctionModel.getAppointmentSaveTime()*60*1000);

            //判断使用时间时分秒
            int resultTime = StoreUtil.dateCompare(mo.getUseTime(), dateSave, 2);
            if ( resultDate == 0 || resultDate == -1){
                if (resultTime == 0 || resultTime == -1 || resultDate == -1){
                    multiIndustryOrderMapper.updateOrderState(mo.getId(), 3);
                    mo.setState(3);
                }
            }

            if (mo.getState() == 1){
                //在原时间加上失效时间
                date.setTime(mo.getOrderTime().getTime() + 30*60*1000);
                int result = DateUtil.compare(new Date(), date);
                if (result == 0 || result > 0) {
                    multiIndustryOrderMapper.updateOrderState(mo.getId(), 4);
                    mo.setState(4);
                }
            }
            orders.add(mo);
        }

        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }


    @Override
    public void updateOrderState(Long id) throws BusinessException {
        McOrderModel mcOrderModel = selectDefaultHead(id);
        if (mcOrderModel.getState() != 1){
            throw new BusinessException("该订单占时无法更改状态！请刷新查看最新状态。");
        }
        multiIndustryOrderMapper.updateOrderState(id, 5);
    }


}

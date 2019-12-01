package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.model.McOrderModel;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface MultiIndustryOrderService {

    /**
     * 多行业下单预约
     * @param record
     * @return
     */
    McOrderModel insertSelective(MultiIndustryOrder record, HttpServletRequest request) throws BusinessException, ParseException;


    /**
     * 查询订单
     * @mbggenerated 2019-11-25
     */
    McOrderModel selectDefaultHead(Long id);

    /**
     * 根据用户编号查询多行业订单
     * @return
     */
    PageInfoModel<List<MultiIndustryOrder>> findOrderListByMemberId(Integer page , Integer size,Integer state);
}

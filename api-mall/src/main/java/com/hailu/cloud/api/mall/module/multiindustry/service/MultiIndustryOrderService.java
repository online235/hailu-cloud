package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MultiIndustryOrderService {

    /**
     * 多行业下单预约
     * @param record
     * @return
     */
    void insertSelective(MultiIndustryOrder record, HttpServletRequest request) throws BusinessException;


    /**
     * 查询订单
     * @mbggenerated 2019-11-25
     */
    MultiIndustryOrder selectByPrimaryKey(Integer id);

    /**
     * 根据用户编号查询多行业订单
     * @param request
     * @return
     */
    PageInfoModel<List<MultiIndustryOrder>> findOrderListByMemberId(HttpServletRequest request, Integer page , Integer size);
}

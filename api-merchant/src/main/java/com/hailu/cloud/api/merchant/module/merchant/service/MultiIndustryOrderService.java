package com.hailu.cloud.api.merchant.module.merchant.service;

import com.hailu.cloud.api.merchant.module.merchant.entity.MultiIndustryOrder;
import com.hailu.cloud.common.model.page.PageInfoModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MultiIndustryOrderService {


    /**
     * 更具商户查询多行业订单
     * @param request
     * @return
     */
    PageInfoModel<List<MultiIndustryOrder>> findOrderListByStoreId(HttpServletRequest request, Integer page, Integer size);
}

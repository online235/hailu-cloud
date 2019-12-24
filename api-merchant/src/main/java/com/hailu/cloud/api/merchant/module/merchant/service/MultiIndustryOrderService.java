package com.hailu.cloud.api.merchant.module.merchant.service;

import com.hailu.cloud.api.merchant.module.merchant.entity.MultiIndustryOrder;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

public interface MultiIndustryOrderService {


    /**
     * 更具商户查询多行业订单
     * @param page
     * @param size
     * @return
     */
    PageInfoModel<List<MultiIndustryOrder>> findOrderListByStoreId(Integer state, Integer page, Integer size);

    /**
     * 根据店铺Id更改订单状态
     * @param id
     * @return
     */
    void updateOrderState(Long id, Integer state);

}

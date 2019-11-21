package com.hailu.cloud.api.mall.module.customerservice.service;

import com.hailu.cloud.api.mall.module.customerservice.vo.CsReasonVo;

import java.util.List;

/**
 * @author Administrator
 */
public interface ICsReasonService {

    /**
     * 获取 售后服务的订单列表
     */
    List<CsReasonVo> findByCsReasonType(int reasonType);

    /**
     * 通过id 查找原因
     *
     * @param csReasonId
     * @return
     */
    CsReasonVo findCsReason(int csReasonId);

}

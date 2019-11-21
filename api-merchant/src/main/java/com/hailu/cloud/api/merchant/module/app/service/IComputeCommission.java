package com.hailu.cloud.api.merchant.module.app.service;

import java.math.BigDecimal;

/**
 * 计算佣金
 *
 * @Author xuzhijie
 * @Date 2019/11/9 21:56
 */
public interface IComputeCommission {

    /**
     * 计算佣金
     * @param commission
     * @return
     */
    BigDecimal compute(BigDecimal commission);

}

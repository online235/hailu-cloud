package com.hailu.cloud.api.mall.module.goods.service.impl;


import com.hailu.cloud.api.mall.module.goods.service.IComputeCommission;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author xuzhijie
 * @Date 2019/11/9 21:57
 */
@Service
public class ComputeCommissionImpl implements IComputeCommission {

    @Value("${merchant.commission.rate.regional-agent:0.15}")
    private BigDecimal regionalAgent;

    @Value("${merchant.commission.rate.service-agent:0.85}")
    private BigDecimal serviceAgent;

    @Override
    public BigDecimal compute(BigDecimal commission,Integer merchantType) {
        if( commission == null ){
            return BigDecimal.ZERO;
        }

        if( merchantType == null || merchantType == 1){
            return commission;
        }
        if( merchantType == 2 ){
            // 服务商
            return commission.multiply(serviceAgent).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}

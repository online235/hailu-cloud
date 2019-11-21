package com.hailu.cloud.api.mall.module.ledger.service;

import com.hailu.cloud.api.mall.module.ledger.po.IncomeTransferOut;
import com.hailu.cloud.api.mall.module.ledger.vo.IncomeTransferOutVo;

import java.util.List;

/**
 * @Author xuzhijie
 * @Date 2019/10/28 9:09
 */
public interface IAuditService {

    /**
     * 查询提现列表
     *
     * @param transferOut 提现查询条件
     * @param beginTime   开始时间
     * @param endTime     结束时间
     * @param pageNo      当前页
     * @param pageSize    每页显示数量
     * @return
     */
    List<IncomeTransferOutVo> list(
            IncomeTransferOut transferOut,
            String beginTime,
            String endTime,
            int pageNo,
            int pageSize);

    /**
     * 审核
     *
     * @param memberId 审核人
     * @param id       提现记录ID
     * @param state    审核状态
     */
    void audit(String memberId, String id, Integer state);

}

package com.hailu.cloud.api.mall.module.ledger.dao;


import com.hailu.cloud.api.mall.module.ledger.vo.IncomeDetailLog;

import java.util.List;

public interface IncomeDetailLogMapper {
    /**
     *
     * @mbggenerated 2019-10-24
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-24
     */
    int insert(IncomeDetailLog record);

    /**
     *
     * @mbggenerated 2019-10-24
     */
    int insertSelective(IncomeDetailLog record);

    /**
     *
     * @mbggenerated 2019-10-24
     */
    IncomeDetailLog selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-24
     */
    int updateByPrimaryKeySelective(IncomeDetailLog record);

    /**
     *
     * @mbggenerated 2019-10-24
     */
    int updateByPrimaryKey(IncomeDetailLog record);

    /**
     * 根据会员ID获取订单
     * @param memberId
     * @return
     */
    List<IncomeDetailLog> findListByMemberId(String memberId);
}
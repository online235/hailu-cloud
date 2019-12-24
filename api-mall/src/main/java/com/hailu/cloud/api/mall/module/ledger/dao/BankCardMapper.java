package com.hailu.cloud.api.mall.module.ledger.dao;

import com.hailu.cloud.api.mall.module.ledger.po.BankCardDto;
import com.hailu.cloud.api.mall.module.ledger.vo.BankCard;

import java.util.List;

public interface BankCardMapper {
    /**
     *
     * @mbggenerated 2019-12-23
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-23
     */
    int insert(BankCard record);

    /**
     *
     * @mbggenerated 2019-12-23
     */
    BankCard selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-23
     */
    int updateByPrimaryKeySelective(BankCard record);

    /**
     *
     * @mbggenerated 2019-12-23
     */
    int updateByPrimaryKey(BankCard record);

    /**
     * 根据会员ID查询列表
     * @param userId
     * @return
     */
    List<BankCardDto> findList(String userId);
}
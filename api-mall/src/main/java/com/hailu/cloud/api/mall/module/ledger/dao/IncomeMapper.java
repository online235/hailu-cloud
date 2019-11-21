package com.hailu.cloud.api.mall.module.ledger.dao;

import com.hailu.cloud.api.mall.module.ledger.vo.Income;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author xuzhijie
 * @Date 2019/10/25 9:17
 */
@Mapper
public interface IncomeMapper {

    /**
     * 查找收入及明细数据
     *
     * @param userId shop_member表的user_id
     * @return
     */
    Income findIncome(@Param("userId") String userId);

    /**
     * 添加
     * @param income
     * @return
     */
    int insert(Income income);

    /**
     * 编辑
     * @param income
     * @return
     */
    int updateByPrimaryKeySelective(Income income);

}

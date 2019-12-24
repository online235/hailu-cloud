package com.hailu.cloud.api.mall.module.ledger.service;

import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.mall.module.ledger.vo.Income;

import javax.validation.ValidationException;
import java.math.BigDecimal;

/**
 * 收入明细
 *
 * @Author xuzhijie
 * @Date 2019/10/25 9:12
 */
public interface IIncomeService {

    /**
     * 提现
     *
     * @param memberId   提现人 shop_member表的user_id
     * @param price      提现金额
     * @param bankCardId   银行卡ID
     */
    boolean transferOut(String memberId, BigDecimal price, Long bankCardId) throws ValidationException;

    /**
     * 获取会员账户金额
     * @param memberId
     * @return
     */
    JSONObject findIncome(String memberId);

    int saveEntity(Income income);

    /**
     * 增加金额
     * @param userId 用户ID
     * @param money 金额
     * @param from 来源
     * @param relevanceId 操作ID
     * @param type 1-冻结金额、2-直接放进可提现金额
     */
    void addAccountByInvitation(String userId, BigDecimal money, String from, String relevanceId, Integer type);

}

package com.hailu.cloud.api.mall.module.ledger.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.mall.module.ledger.dao.IncomeMapper;
import com.hailu.cloud.api.mall.module.ledger.dao.IncomeTransferOutMapper;
import com.hailu.cloud.api.mall.module.ledger.service.IIncomeService;
import com.hailu.cloud.api.mall.module.ledger.vo.Income;
import com.hailu.cloud.api.mall.util.Const;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author xuzhijie
 * @Date 2019/10/25 9:16
 */
@Slf4j
@Service
public class IncomeServiceImpl implements IIncomeService {

    @Resource
    private IncomeTransferOutMapper incomeTransferOutMapper;

    @Resource
    private IncomeMapper incomeMapper;

    @Resource
    private IncomeDetailLogService incomeDetailLogService;

    @Resource
    BasicFeignClient basicFeignClient;

    @Override
    public boolean transferOut(String memberId, BigDecimal price, String bankCard, String cardholder) {
        BigDecimal transferMoney = BigDecimal.valueOf(Const.TRANSFEROUTTHRESHOLD);
        if (price.compareTo(transferMoney) < 0) {
            log.warn("提现人：{}，最小提现金额应为：{}", memberId, Const.TRANSFEROUTTHRESHOLD);
            throw new ValidationException("最小提现金额为" + Const.TRANSFEROUTTHRESHOLD);
        }
        //校验金额是否足够
        Income income = incomeMapper.findIncome(memberId);

        //当前金额
        BigDecimal money = income.getPrice();
        BigDecimal afterMoney = money.subtract(price);
        if (afterMoney.compareTo(computeAvailableTransferOutAmount(money)) > -1) {
            log.warn("提现人：{}，剩余提现金额为：{}，不足够提现", computeAvailableTransferOutAmount(money));
            return false;
        }
        //将提现金额冻结  更改剩余金额
        income.setFreezePrice(price);
        income.setPrice(afterMoney);
        int status = saveEntity(income);
        //乐观锁处理金额处理
        if (status != 1) {
            return transferOut(memberId, price, bankCard, cardholder);
        }
        //生成提现记录
        Long id = basicFeignClient.uuid().getData();
        incomeTransferOutMapper.transferOut(
                id,
                memberId,
                price,
                bankCard,
                cardholder
        );

        //增加提现明细
        incomeDetailLogService.addAccountDetailLog(String.valueOf(id), price, money, afterMoney, "提现", 1, memberId);

        return true;
    }

    @Override
    public JSONObject findIncome(String memberId) {
        Income income = incomeMapper.findIncome(memberId);
        JSONObject resultJs = new JSONObject();
        BigDecimal price = BigDecimal.valueOf(0);
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        if (income != null) {
            //余额
            price = income.getPrice();
            totalPrice = income.getTotalPrice();
        }
        //累计收入
        resultJs.put("totalPrice", totalPrice);
        resultJs.put("price", price == null ? 0 : price);
        //可提现金额
        BigDecimal transferOut = BigDecimal.valueOf(Const.TRANSFEROUTTHRESHOLD);
        if (price == null || price.compareTo(transferOut) < 0) {
            resultJs.put("availableWithdrawMerchants", 0.00);
        } else {
            resultJs.put("availableWithdrawMerchants", computeAvailableTransferOutAmount(price));
        }
        return resultJs;
    }

    /**
     * 计算可用提现金额
     * 可用提现金额 = 余额 - 冻结 - 尾数
     * 例（100 为 transferOutThreshold值） ：
     * 108 只能提现 100
     * 198 只能提现 100
     * 198958 只能提现 198900
     *
     * @param price
     * @param
     * @return
     */
    private BigDecimal computeAvailableTransferOutAmount(BigDecimal price) {
        return price.subtract(BigDecimal.valueOf(Const.TRANSFEROUTTHRESHOLD));
    }

    @Override
    public void addAccountByInvitation(String userId, BigDecimal money, String from, String relevanceId, Integer type) {
        try {
            if (userId == null) {
                return;
            }
            Income income = incomeMapper.findIncome(userId);

            if (income == null) {
                income = new Income();
                income.setMemberId(userId);
            }

            BigDecimal totalMoney = income.getPrice();
            totalMoney = totalMoney == null ? new BigDecimal("0") : totalMoney;
            BigDecimal afterMoney = totalMoney.add(money);
            //校验增加金额是否需要放进冻结金额
            if (type == 1) {
                //增加收入冻结金额
                BigDecimal inFreezePrice = income.getInFreezePrice();
                inFreezePrice = inFreezePrice == null ? BigDecimal.valueOf(0) : inFreezePrice;
                income.setInFreezePrice(inFreezePrice.add(money));
            } else {
                //增加总金额
                income.setPrice(afterMoney);
            }

            //增加累计金额
            BigDecimal totalPrice = income.getTotalPrice();
            totalPrice = totalPrice == null ? new BigDecimal("0") : totalPrice;
            income.setTotalPrice(totalPrice.add(money));
            int status = saveEntity(income);
            if (status != 1) {
                addAccountByInvitation(userId, money, from, relevanceId, type);
                return;
            }

            //添加资金流向
            if (StringUtils.isBlank(relevanceId)) {
                relevanceId = userId;
            }
            incomeDetailLogService.addAccountDetailLog(relevanceId, money, totalMoney, afterMoney, from, 2, userId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 保存或编辑
     *
     * @param income
     * @return
     */
    @Override
    public int saveEntity(Income income) {
        income.setUpdateTime(new Date());
        if (income.getId() == null) {
            income.setId(basicFeignClient.uuid().getData());
            income.setStatus(1);
            return incomeMapper.insert(income);
        }
        return incomeMapper.updateByPrimaryKeySelective(income);
    }

}

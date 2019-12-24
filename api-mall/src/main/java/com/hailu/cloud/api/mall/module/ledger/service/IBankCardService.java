package com.hailu.cloud.api.mall.module.ledger.service;

import com.hailu.cloud.api.mall.module.ledger.po.BankCardDto;
import com.hailu.cloud.api.mall.module.ledger.vo.BankCard;

import java.util.List;

/**
 * 银行卡
 * @author junpei.deng
 */
public interface IBankCardService {

    /**
     * 保存或修改
     * @param bankCard
     * @return
     */
    BankCardDto save(BankCardDto bankCard);

    /**
     * 获取列表
     * @return
     */
    List<BankCardDto> findList();

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    BankCard findById(Long id);
}

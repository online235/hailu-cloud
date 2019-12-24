package com.hailu.cloud.api.mall.module.ledger.service.impl;

import com.hailu.cloud.api.mall.module.ledger.dao.BankCardMapper;
import com.hailu.cloud.api.mall.module.ledger.po.BankCardDto;
import com.hailu.cloud.api.mall.module.ledger.service.IBankCardService;
import com.hailu.cloud.api.mall.module.ledger.vo.BankCard;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 银行卡
 * @author junpei.deng
 */
@Service
public class BankCardServiceImpl implements IBankCardService {

    @Resource
    private BankCardMapper bankCardMapper;

    @Resource
    private BasicFeignClient basicFeignClient;


    @Override
    public BankCardDto save(BankCardDto bankCardDto){
        BankCard bankCard = new BankCard();
        BeanUtils.copyProperties(bankCardDto,bankCard);
        bankCard.setUserId(RequestUtils.getMemberLoginInfo().getUserId());
        saveEntity(bankCard);
        bankCardDto.setId(bankCard.getId());
        return bankCardDto;
    }


    @Override
    public List<BankCardDto> findList() {
        return bankCardMapper.findList(RequestUtils.getMemberLoginInfo().getUserId());
    }

    /**
     * 保存实体
     * @param bankCard
     * @return
     */
    private BankCard saveEntity(BankCard bankCard){
        Date dataNow = new Date();
        String userId = RequestUtils.getMemberLoginInfo().getUserId();
        bankCard.setModifyTime(dataNow);
        bankCard.setModifyBy(userId);
        if(bankCard.getId() == null){
            bankCard.setId(basicFeignClient.uuid().getData());
            bankCard.setStatus(1);
            bankCard.setCreateBy(userId);
            bankCard.setCreateTime(dataNow);
            bankCardMapper.insert(bankCard);
            return bankCard;
        }
        bankCardMapper.updateByPrimaryKeySelective(bankCard);
        return bankCard;
    }


    @Override
    public BankCard findById(Long id) {
        if(id == null){
            return null;
        }
        return bankCardMapper.selectByPrimaryKey(id);
    }
}

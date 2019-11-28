package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.StoreUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreInformationImpl implements StoreInformationService {

    @Resource
    private StoreInformationMapper storeInformationMapper;

    @Override
    public Object findStoreInformationList(Long storeTotalType, Long storeSonType, Integer size, Integer page) {
        Page p = PageHelper.startPage(page, size);
        List<StoreInformation> datas = storeInformationMapper.findStoreInformationList(storeTotalType, storeSonType);
        List<StoreInformation> data = new ArrayList<>();
        for (StoreInformation storeInformation : datas){
            storeInformation.setBusinessState(StoreUtil.storeStatus(storeInformation.getOpeningTime(),storeInformation.getClosingTime(),storeInformation.getWeekDay()) == true ? Long.parseLong("1") : Long.parseLong("2"));
            data.add(storeInformation);
        }
        return new PageInfoModel<>(p.getPages(),p.getTotal(),data);
    }

    @Override
    public StoreInformation findStoreInformation(Long id) throws BusinessException {
        StoreInformation storeInformation = storeInformationMapper.findStoreInformation(id);
        if (storeInformation != null){
            return storeInformation;
        }
        throw new BusinessException("商店未营业");
    }
}

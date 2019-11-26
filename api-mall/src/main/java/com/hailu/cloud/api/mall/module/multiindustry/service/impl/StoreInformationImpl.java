package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreInformationImpl implements StoreInformationService {

    @Resource
    private StoreInformationMapper storeInformationMapper;

    @Override
    public Object findStoreInformationList(Integer storeTotalType, Integer storeSonType, Integer size, Integer page) {
        PageHelper.startPage(page, size);
        PageInfo pageInfo = new PageInfo(storeInformationMapper.findStoreInformationList(storeTotalType, storeSonType));
        return pageInfo;
    }

    @Override
    public StoreInformation findStoreInformation(Integer id) {
        return storeInformationMapper.findStoreInformation(id);
    }
}

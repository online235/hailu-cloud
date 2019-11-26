package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreInformationImpl implements StoreInformationService {

    @Resource
    private StoreInformationMapper storeInformationMapper;

    @Override
    public Object findStoreInformationList(Integer storeTotalType, Integer storeSonType, Integer size, Integer page) {
        Page p = PageHelper.startPage(page, size);
        List<StoreInformation> datas = storeInformationMapper.findStoreInformationList(storeTotalType, storeSonType);
        return new PageInfoModel<>(p.getPages(),p.getTotal(),datas);
    }

    @Override
    public StoreInformation findStoreInformation(Long id) {
        return storeInformationMapper.findStoreInformation(id);
    }
}

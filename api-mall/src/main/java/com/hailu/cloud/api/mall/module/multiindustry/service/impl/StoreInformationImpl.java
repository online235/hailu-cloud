package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationResultModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreAlbumServier;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.StoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreInformationImpl implements StoreInformationService {

    @Resource
    private StoreInformationMapper storeInformationMapper;

    @Autowired
    private StoreAlbumServier storeAlbumServier;

    @Override
    public PageInfoModel<List<StoreInformation>> findStoreInformationList(Long storeTotalType, Long storeSonType, String cityCode, Integer size, Integer page) throws ParseException {
        Page p = PageHelper.startPage(page, size);
        List<StoreInformation> datas = storeInformationMapper.findStoreInformationList(storeTotalType, storeSonType ,cityCode);
        List<StoreInformation> data = new ArrayList<>();
        for (StoreInformation storeInformation : datas){
            if(storeInformation.getBusinessState() == 1){
                storeInformation.setBusinessState(StoreUtil.storeStatus(storeInformation.getOpeningTime(),storeInformation.getClosingTime(),storeInformation.getWeekDay()) == true ? 1 : 2);
                data.add(storeInformation);
            }
        }
        return new PageInfoModel<>(p.getPages(),p.getTotal(),data);
    }

    @Override
    public StoreInformation findStoreInformation(Long id) throws BusinessException {
        StoreInformation storeInformation = storeInformationMapper.findStoreInformation(id);
        if (storeInformation == null){
            throw new BusinessException("商店未营业或者不存在");
        }
        return storeInformation;

    }


    @Override
    public StoreInformationResultModel findStoreInformationLeftAlbum(Long id) throws BusinessException {
        StoreInformationResultModel storeInformationResultModelList = storeInformationMapper.findStoreInformationLeftAlbum(id);
        if (storeInformationResultModelList == null){
            throw new BusinessException("商店未营业或者不存在");
        }
        return storeInformationResultModelList;
    }
}

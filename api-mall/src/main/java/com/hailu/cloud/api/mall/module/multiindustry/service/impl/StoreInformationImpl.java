package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.ManagementType;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationListResult;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationResultModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.ManagementTypeService;
import com.hailu.cloud.api.mall.module.multiindustry.service.McStoreAlbumMallService;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreInformationService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.StoreUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreInformationImpl implements StoreInformationService {

    @Resource
    private StoreInformationMapper storeInformationMapper;

    @Resource
    private ManagementTypeService managementTypeService;

    @Resource
    private McStoreAlbumMallService mcStoreAlbumMallService;



    @Override
    public PageInfoModel<List<StoreInformationListResult>> findStoreInformationList(
            Long storeTotalType,
            Long storeSonType,
            String cityCode,
            String areaCode,
            Integer tagId,
            String shopName,
            Integer priceRanking,
            Double startingPrice,
            Double closingPrice,
            Integer size,
            Integer page) throws ParseException {

        Page p = PageHelper.startPage(page, size);
        List<StoreInformationListResult> datas = storeInformationMapper.findStoreInformationList(
                storeTotalType,
                storeSonType,
                cityCode,
                areaCode,
                tagId,
                shopName,
                priceRanking,
                startingPrice,
                closingPrice);
        List<StoreInformationListResult> data = new ArrayList<>();
        for (StoreInformationListResult storeInformationListResult : datas){
            if(storeInformationListResult.getBusinessState() == 1){
                storeInformationListResult.setBusinessState(StoreUtil.storeBusinessTimeStatus(storeInformationListResult.getBusinessTime(), storeInformationListResult.getWeekDay()) ? 1 : 2);
            }
            data.add(storeInformationListResult);
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
        if(storeInformationResultModelList.getStoreTotalType() != null){
            ManagementType managementType = managementTypeService.findManagementById(storeInformationResultModelList.getStoreTotalType());
            storeInformationResultModelList.setStoreTotalTypeName(managementType.getManagementName());
        }
        if(storeInformationResultModelList.getStoreSonType() != null){
            ManagementType managementType = managementTypeService.findManagementById(storeInformationResultModelList.getStoreSonType());
            storeInformationResultModelList.setStoreTypeName(managementType.getManagementName());
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("storeId", id);
        map.put("albumTypeIsNotRotation", 1);
        List<McStoreAlbum> mcStoreAlbums =  mcStoreAlbumMallService.findListByParam(map);
        storeInformationResultModelList.setAlbumNum(mcStoreAlbums.size());
        return storeInformationResultModelList;
    }


}

package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreInformationMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
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
    public Object findStoreInformationList(Long storeTotalType, Long storeSonType, String cityCode, Integer size, Integer page) throws ParseException {
        Page p = PageHelper.startPage(page, size);
        List<StoreInformation> datas = storeInformationMapper.findStoreInformationList(storeTotalType, storeSonType ,cityCode);
        List<StoreInformation> data = new ArrayList<>();
        for (StoreInformation storeInformation : datas){
            storeInformation.setBusinessState(StoreUtil.storeStatus(storeInformation.getOpeningTime(),storeInformation.getClosingTime(),storeInformation.getWeekDay()) == true ? 1L : 2L);
            data.add(storeInformation);
        }
        return new PageInfoModel<>(p.getPages(),p.getTotal(),data);
    }

    @Override
    public Object findStoreInformation(Long id) throws BusinessException {
        StoreInformation storeInformation = storeInformationMapper.findStoreInformation(id);
        List<StoreAlbum> storeAlbums = storeAlbumServier.findStoreAlbumList(storeInformation.getId());
        JSONArray objects = new JSONArray();
        JSONArray object = new JSONArray();

        for (StoreAlbum storeAlbum : storeAlbums){
            object.add(storeAlbum.getAlbumUrl());
        }
        objects.add(jsonObjectByStoreInformation(object,storeInformation));
        if (storeInformation != null){
            return objects;
        }
        throw new BusinessException("商店未营业");
    }
    
    public JSONObject jsonObjectByStoreInformation(JSONArray object,StoreInformation storeInformation){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",storeInformation.getId());
        jsonObject.put("mcNumberId",storeInformation.getMcNumberId());
        jsonObject.put("shopName",storeInformation.getShopName());
        jsonObject.put("phone",storeInformation.getPhone());
        jsonObject.put("provinceCode",storeInformation.getProvinceCode());
        jsonObject.put("cityCode",storeInformation.getCityCode());
        jsonObject.put("areaCode",storeInformation.getAreaCode());
        jsonObject.put("detailAddress",storeInformation.getDetailAddress());
        jsonObject.put("storeTotalType",storeInformation.getStoreTotalType());
        jsonObject.put("storeSonType",storeInformation.getStoreSonType());
        jsonObject.put("businessState",storeInformation.getBusinessState());
        jsonObject.put("businessStateDisplay",storeInformation.getBusinessStateDisplay());
        jsonObject.put("closingTime",storeInformation.getClosingTime());
        jsonObject.put("openingTime",storeInformation.getOpeningTime());
        jsonObject.put("createdat",storeInformation.getCreatedat());
        jsonObject.put("updatedat",storeInformation.getUpdatedat());
        jsonObject.put("defaultHead",storeInformation.getDefaultHead());
        jsonObject.put("toExamine",storeInformation.getToExamine());
        jsonObject.put("toExamineDisplay",storeInformation.getToExamineDisplay());
        jsonObject.put("weekDay",storeInformation.getWeekDay());
        jsonObject.put("weekDayDisplay",storeInformation.getWeekDayDisplay());
        jsonObject.put("albumUrlList",object);
        return jsonObject;
    }
}

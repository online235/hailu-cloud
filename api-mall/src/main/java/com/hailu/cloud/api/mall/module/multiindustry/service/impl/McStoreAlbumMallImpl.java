package com.hailu.cloud.api.mall.module.multiindustry.service.impl;


import com.hailu.cloud.api.mall.module.multiindustry.dao.McStoreAlbumMallMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.model.RotationStoreModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreAlbumListModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.McStoreAlbumMallService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import jodd.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class McStoreAlbumMallImpl implements McStoreAlbumMallService {



    @Resource
    private McStoreAlbumMallMapper mcStoreAlbumMallMapper;

    @Autowired
    private BasicFeignClient uuidFeignClient;



    @Override
    public void insertSelective(McStoreAlbum mcStoreAlbum){

        mcStoreAlbum.setId(uuidFeignClient.uuid().getData());
        mcStoreAlbum.setCreateTime(new Date());
        mcStoreAlbum.setUpdateTime(new Date());
        mcStoreAlbumMallMapper.insertSelective(mcStoreAlbum);
    }

    @Override
    public List<McStoreAlbum> findListByParam(Object parameter) {
        return mcStoreAlbumMallMapper.findListByParam(parameter);
    }


    @Override
    public StoreAlbumListModel findStoreAlbumListModel(Long storeId) {

        StoreAlbumListModel storeAlbumListModel = new StoreAlbumListModel();
        Map<String, Object> map = new HashMap<>(2);
        map.put("storeId", storeId);
        map.put("albumTypeIsNotRotation", 1);
        List<McStoreAlbum> mcStoreAlbums =  mcStoreAlbumMallMapper.findListByParam(map);
        if(!CollectionUtils.isEmpty(mcStoreAlbums)){
            List<McStoreAlbum> environmentalStoreAlbumList = mcStoreAlbums.stream().filter(mcStoreAlbum -> mcStoreAlbum.getAlbumType() == 1).collect(Collectors.toList());
            List<McStoreAlbum> otherStoreAlbumList = mcStoreAlbums.stream().filter(mcStoreAlbum -> mcStoreAlbum.getAlbumType() == 2).collect(Collectors.toList());
            storeAlbumListModel.setMcStoreAlbumList(mcStoreAlbums);
            storeAlbumListModel.setEnvironmentalStoreAlbumList(environmentalStoreAlbumList);
            storeAlbumListModel.setOtherStoreAlbumList(otherStoreAlbumList);
        }
        return storeAlbumListModel;
    }

    @Override
    public List<RotationStoreModel> findStoreAlbumList(Long storeId, Integer albumType) {
        return mcStoreAlbumMallMapper.findStoreAlbumList(storeId,albumType);
    }


}

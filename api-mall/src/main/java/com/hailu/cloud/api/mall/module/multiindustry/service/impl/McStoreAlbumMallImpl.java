package com.hailu.cloud.api.mall.module.multiindustry.service.impl;


import com.hailu.cloud.api.mall.module.multiindustry.dao.McStoreAlbumMallMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.service.McStoreAlbumMallService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


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
    public void updateByPrimaryKey(McStoreAlbum mcStoreAlbum){
        mcStoreAlbumMallMapper.updateByPrimaryKey(mcStoreAlbum);
    }



    @Override
    public List<McStoreAlbum> findListByParam(Object parameter){
        return mcStoreAlbumMallMapper.findListByParam(parameter);
    }




    @Override
    public McStoreAlbum findObjectById(Long id){
        return mcStoreAlbumMallMapper.findObjectById(id);
    }



    @Override
    public void deleteById(Long id){
        mcStoreAlbumMallMapper.deleteById(id);
    }



    @Override
    public void  insertStoreAlbumList(Object parameter){
        mcStoreAlbumMallMapper.insertStoreAlbumList(parameter);
    }


    /**
     * 根据店铺id批量删除相册数据
     */
    @Override
    public void deleteStoreAlbumByStoreId(Long storeId){

        Map map = new HashMap();
        map.put("storeId",storeId);
        List<McStoreAlbum> mcStoreAlbumList = mcStoreAlbumMallMapper.findListByParam(map);
        if(mcStoreAlbumList.size()>0){
            List<Long> idList = new ArrayList<>();
            for(McStoreAlbum mcStoreAlbum:mcStoreAlbumList){
                idList.add(mcStoreAlbum.getId());
            }
            map.clear();
            map.put("idList",idList);
            mcStoreAlbumMallMapper.deleteByIdList(map);
        }
    }


    /**
     * 批量删除
     */
    @Override
    public void deleteByIds(Object parameter){
        mcStoreAlbumMallMapper.deleteByIdList(parameter);
    }



}

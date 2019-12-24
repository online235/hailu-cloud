package com.hailu.cloud.api.merchant.module.merchant.service.impl;


import com.hailu.cloud.api.merchant.module.merchant.dao.McStoreAlbumMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreAlbum;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class McStoreAlbumService {


    @Resource
    private McStoreAlbumMapper mcStoreAlbumMapper;

    @Autowired
    private BasicFeignClient uuidFeignClient;


    public void insertSelective(McStoreAlbum mcStoreAlbum){

        mcStoreAlbum.setId(uuidFeignClient.uuid().getData());
        mcStoreAlbum.setCreateTime(new Date());
        mcStoreAlbum.setUpdateTime(new Date());
        mcStoreAlbumMapper.insertSelective(mcStoreAlbum);
    }


    public void updateByPrimaryKey(McStoreAlbum mcStoreAlbum){

        mcStoreAlbum.setUpdateTime(new Date());
        mcStoreAlbumMapper.updateByPrimaryKey(mcStoreAlbum);
    }


    public List<McStoreAlbum> findListByParam(Object parameter){
        return mcStoreAlbumMapper.findListByParam(parameter);
    }


    public McStoreAlbum findObjectById(Long id){
        return mcStoreAlbumMapper.findObjectById(id);
    }


    public void deleteById(Long id){
        mcStoreAlbumMapper.deleteById(id);
    }

    public void  insertStoreAlbumList(Object parameter){
        mcStoreAlbumMapper.insertStoreAlbumList(parameter);
    }




    /**
     * 根据店铺id批量删除相册数据
     */
    public void deleteStoreAlbumByStoreId(Long storeId){

        Map map = new HashMap();
        map.put("storeId",storeId);
        List<McStoreAlbum> mcStoreAlbumList = mcStoreAlbumMapper.findListByParam(map);
        if(mcStoreAlbumList.size()>0){
            List<Long> idList = new ArrayList<>();
            for(McStoreAlbum mcStoreAlbum:mcStoreAlbumList){
                idList.add(mcStoreAlbum.getId());
            }
            map.clear();
            map.put("idList",idList);
            mcStoreAlbumMapper.deleteByIdList(map);
        }
    }


    /**
     * 批量删除
     */
    public void deleteByIds(Object parameter){
        mcStoreAlbumMapper.deleteByIdList(parameter);
    }


}

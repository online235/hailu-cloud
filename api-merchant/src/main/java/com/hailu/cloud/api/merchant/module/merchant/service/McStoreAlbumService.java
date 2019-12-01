package com.hailu.cloud.api.merchant.module.merchant.service;


import com.hailu.cloud.api.merchant.module.merchant.dao.McStoreAlbumMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreAlbum;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        mcStoreAlbumMapper.updateByPrimaryKey(mcStoreAlbum);
    }


    public List<McStoreAlbum> findListByParam(Object parameter){
        return mcStoreAlbumMapper.findListByParam(parameter);
    }


    public McStoreAlbum findObjectById(Long id){
        return mcStoreAlbumMapper.findObjectById(id);
    }





}

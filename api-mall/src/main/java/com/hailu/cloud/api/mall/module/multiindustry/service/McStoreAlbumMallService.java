package com.hailu.cloud.api.mall.module.multiindustry.service;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import java.util.*;

public interface McStoreAlbumMallService {




    public void insertSelective(McStoreAlbum mcStoreAlbum);


    public void updateByPrimaryKey(McStoreAlbum mcStoreAlbum);


    public List<McStoreAlbum> findListByParam(Object parameter);



    public McStoreAlbum findObjectById(Long id);


    public void deleteById(Long id);

    public void  insertStoreAlbumList(Object parameter);


    /**
     * 根据店铺id批量删除相册数据
     */
    public void deleteStoreAlbumByStoreId(Long storeId);


    /**
     * 批量删除
     */
    public void deleteByIds(Object parameter);


}

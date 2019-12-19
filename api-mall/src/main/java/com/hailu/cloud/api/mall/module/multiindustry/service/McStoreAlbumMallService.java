package com.hailu.cloud.api.mall.module.multiindustry.service;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import java.util.*;

public interface McStoreAlbumMallService {




    void insertSelective(McStoreAlbum mcStoreAlbum);


    void updateByPrimaryKey(McStoreAlbum mcStoreAlbum);


    List<McStoreAlbum> findListByParam(Object parameter);



    McStoreAlbum findObjectById(Long id);


    void deleteById(Long id);

    void  insertStoreAlbumList(Object parameter);


    /**
     * 根据店铺id批量删除相册数据
     */
    void deleteStoreAlbumByStoreId(Long storeId);


    /**
     * 批量删除
     */
    void deleteByIds(Object parameter);


}

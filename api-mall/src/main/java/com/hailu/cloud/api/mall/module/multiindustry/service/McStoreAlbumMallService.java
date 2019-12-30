package com.hailu.cloud.api.mall.module.multiindustry.service;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.model.RotationStoreModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreAlbumListModel;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface McStoreAlbumMallService {




    void insertSelective(McStoreAlbum mcStoreAlbum);

    /**
     * 获取数据列表
     * @param parameter
     * @return
     */
    List<McStoreAlbum> findListByParam(Object parameter);

    /**
     * 获取店铺相册
     * @return
     */
    StoreAlbumListModel findStoreAlbumListModel(Long storeId);

    /**
     * 根据店铺编号类型查询店铺的相册
     * @param storeId
     * @return
     */
    List<RotationStoreModel> findStoreAlbumList(@Param("storeId") Long storeId, @Param("albumType") Integer albumType);



}

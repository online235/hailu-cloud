package com.hailu.cloud.api.mall.module.multiindustry.service;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreAlbumListModel;

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


}

package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreAlbum;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺相册
 * @Date: 2019/12/3 0003
 * @program: cloud
 * @create: 2019-12-03 15:
 */
public interface StoreAlbumServier {

    /**
     * 根据店铺编号查询店铺的相册
     * @param storeId
     * @return
     */
    List<StoreAlbum> findStoreAlbumList(Long storeId);

}

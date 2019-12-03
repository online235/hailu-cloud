package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreAlbum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreAlbumMapper {
    /**
     * 根据店铺编号查询店铺的相册
     * @param storeId
     * @return
     */
    List<StoreAlbum> findStoreAlbumList(@Param("storeId") Long storeId);
}
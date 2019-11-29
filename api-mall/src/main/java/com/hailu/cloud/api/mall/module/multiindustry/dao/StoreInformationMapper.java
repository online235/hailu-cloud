package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreInformationMapper {

    /**
     * 分类查询商铺
     * @param storeTotalType
     * @param storeSonType
     * @return
     */
    List<StoreInformation> findStoreInformationList(@Param("storeTotalType") Long storeTotalType, @Param("storeSonType") Long storeSonType, @Param("cityCode") String cityCode);

    /**
     * 店铺详细信息
     * @param id
     * @return
     */
    StoreInformation findStoreInformation(@Param("id") Long id);
}
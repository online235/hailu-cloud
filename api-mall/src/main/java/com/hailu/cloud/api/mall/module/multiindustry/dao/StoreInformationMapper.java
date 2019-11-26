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
    List<StoreInformation> findStoreInformationList(@Param("storeTotalType") Integer storeTotalType, @Param("storeSonType") Integer storeSonType);

    /**
     * 店铺详细信息
     * @param id
     * @return
     */
    StoreInformation findStoreInformation(Integer id);
}
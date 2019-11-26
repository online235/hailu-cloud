package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;

import java.util.List;

public interface StoreInformationService {

    /**
     * 分类查询商铺
     * @param storeTotalType
     * @param storeSonType
     * @return
     */
    Object findStoreInformationList(Integer storeTotalType, Integer storeSonType,  Integer size, Integer page);

    /**
     * 店铺详细信息
     * @param id
     * @return
     */
    StoreInformation findStoreInformation(Long id);
}

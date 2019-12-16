package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McShopTag;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺标签
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 15:
 */
public interface McShopTagService {

    /**
     * 根据店铺编号查询店铺下的标签
     * @param storeId
     * @return
     */
    List<McShopTag> findMcShopTagListByStoreId(Long storeId);
}

package com.hailu.cloud.api.merchant.module.merchant.service;

import com.hailu.cloud.api.merchant.module.merchant.entity.McShopTag;
import com.hailu.cloud.api.merchant.module.merchant.model.McShopTagModel;

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


    /**
     * 根据店铺编号查询店铺下的标签名称
     * @param storeId
     * @return
     */
    List<McShopTagModel> findMcShopTagModelListByStoreId(Long storeId);



    /**
     * 插入多条店铺数据
     * @param tagId
     * @param storeId
     * @return
     */
    List<McShopTag> addMcSHopTag(Long[] tagId, Long storeId);

    /**
     * 更新多条或者一条数据
     * @param tagId
     * @param storeId
     * @return
     */
    List<McShopTag> updateMcShopTag(Long[] tagId, Long storeId);

    /**
     * 停用标签
     * @param storeId
     * @return
     */
    int updateMcShopTagByStoreId(Long storeId);

    /**
     * 查询店铺是否用过此标签
     * @param tagId
     * @param storeId
     * @return
     */
    int findMcShopTagByIdAndStoreId(Long tagId, Long storeId);
}

package com.hailu.cloud.api.merchant.module.merchant.dao;

import com.hailu.cloud.api.merchant.module.merchant.entity.McShopTag;
import com.hailu.cloud.api.merchant.module.merchant.model.McShopTagModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description  ：店铺标签
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/16 0016 15:59
 */
@Mapper
public interface McShopTagMapper {

    /**
     * 根据店铺编号查询店铺下的标签
     * @param storeId
     * @return
     */
    List<McShopTag> findMcShopTagListByStoreId(@Param("storeId") Long storeId);


    /**
     * 根据店铺编号查询店铺下的标签名称
     * @param storeId
     * @return
     */
    List<McShopTagModel> findMcShopTagModelListByStoreId(@Param("storeId") Long storeId);


    /**
     * 插入多条店铺数据
     * @param tag
     * @return
     */
    int addMcShopTag(List<McShopTag> tag);

    /**
     * 更新多条或者一条数据
     * @param tag
     * @return
     */
    int updateMcShopTag(List<McShopTag> tag);

    /**
     * 停用标签
     * @param storeId
     * @return
     */
    int updateMcShopTagByStoreId(@Param("storeId") Long storeId);

    /**
     * 查询店铺是否用过此标签
     * @param tagId
     * @param storeId
     * @return
     */
    int findMcShopTagByIdAndStoreId(@Param("tagId") Long tagId, @Param("storeId") Long storeId);

}
package com.hailu.cloud.api.admin.module.merchant.dao;

import com.hailu.cloud.api.admin.module.merchant.entity.McShopTag;
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


}
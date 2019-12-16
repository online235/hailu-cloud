package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McShopTag;
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
     *
     * @mbggenerated 2019-12-16
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-16
     */
    int insert(McShopTag record);

    /**
     *
     * @mbggenerated 2019-12-16
     */
    int insertSelective(McShopTag record);

    /**
     *
     * @mbggenerated 2019-12-16
     */
    McShopTag selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-16
     */
    int updateByPrimaryKeySelective(McShopTag record);

    /**
     *
     * @mbggenerated 2019-12-16
     */
    int updateByPrimaryKey(McShopTag record);

    /**
     * 根据店铺编号查询店铺下的标签
     * @param storeId
     * @return
     */
    List<McShopTag> findMcShopTagListByStoreId(@Param("storeId") Long storeId);
}
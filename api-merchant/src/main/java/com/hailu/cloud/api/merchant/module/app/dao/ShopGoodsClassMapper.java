package com.hailu.cloud.api.merchant.module.app.dao;

import com.hailu.cloud.api.merchant.module.app.entity.GoodsClass;
import com.hailu.cloud.api.merchant.module.app.model.GoodsClassVo;

import java.util.List;

public interface ShopGoodsClassMapper {
    /**
     *
     * @mbggenerated 2019-11-20
     */
    int deleteByPrimaryKey(Integer gcId);

    /**
     *
     * @mbggenerated 2019-11-20
     */
    int insert(GoodsClass record);

    /**
     *
     * @mbggenerated 2019-11-20
     */
    int insertSelective(GoodsClass record);

    /**
     *
     * @mbggenerated 2019-11-20
     */
    GoodsClass selectByPrimaryKey(Integer gcId);

    /**
     *
     * @mbggenerated 2019-11-20
     */
    int updateByPrimaryKeySelective(GoodsClass record);

    /**
     *
     * @mbggenerated 2019-11-20
     */
    int updateByPrimaryKeyWithBLOBs(GoodsClass record);

    /**
     *
     * @mbggenerated 2019-11-20
     */
    int updateByPrimaryKey(GoodsClass record);

    List<GoodsClassVo> findGoodsClassList(String gcParentId);
}
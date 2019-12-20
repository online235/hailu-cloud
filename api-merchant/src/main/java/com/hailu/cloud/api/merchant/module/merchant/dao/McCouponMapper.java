package com.hailu.cloud.api.merchant.module.merchant.dao;

import com.hailu.cloud.api.merchant.module.merchant.entity.McCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/20 0020 10:29
 */
@Mapper
public interface McCouponMapper {
    /**
     *
     * @mbggenerated 2019-12-20
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加到店卷
     * @mbggenerated 2019-12-20
     */
    int insertSelective(McCoupon record);

    /**
     * 查询店铺详细信息
     * @mbggenerated 2019-12-20
     */
    McCoupon selectByPrimaryKey(@Param("id") Long id, @Param("numberId") Long numberId);

    /**
     * 更改信息
     * @mbggenerated 2019-12-20
     */
    int updateByPrimaryKeySelective(McCoupon record);

    /**
     * 到店卷列表
     */
    List<McCoupon> findMcCouponList(
            @Param("volumeName") String volumeName,
            @Param("toExamine") Integer toExamine,
            @Param("shelfState") Integer shelfState);

    /**
     * 到店卷下架
     * @param id
     * @return
     */
    int updShelfStateById(@Param("id") Long id,@Param("shelfState") Integer shelfState);
}
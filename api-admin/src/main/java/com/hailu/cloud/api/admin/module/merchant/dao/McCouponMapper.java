package com.hailu.cloud.api.admin.module.merchant.dao;

import com.hailu.cloud.api.admin.module.merchant.entity.McCoupon;
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
     * 删除到店卷
     * @mbggenerated 2019-12-20
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 查询到店卷详细信息
     * @mbggenerated 2019-12-20
     */
    McCoupon selectByPrimaryKey(@Param("id") Long id);

    /**
     * 到店卷列表
     */
    List<McCoupon> findMcCouponList(
            @Param("storeTotalType") Long storeTotalType,
            @Param("volumeName") String volumeName,
            @Param("toExamine") Integer toExamine,
            @Param("shelfState") Integer shelfState);

    /**
     * 更改审核状态
     * @param id
     * @param toExamine
     * @return
     */
    int updMcCouponById(@Param("id") Long id,@Param("toExamine") Integer toExamine, @Param("state") int state);

}
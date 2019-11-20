package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.PayExpand;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayExpandMapper {
    /**
     *
     * @mbggenerated 2019-10-17
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int insert(PayExpand record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int insertSelective(PayExpand record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    PayExpand selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int updateByPrimaryKeySelective(PayExpand record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int updateByPrimaryKey(PayExpand record);

    /**
     * 根据支付ID获取支付信息
     * @param payId
     * @return
     */
    List<PayExpand> findListByPayId(@Param("payId") String payId);
}
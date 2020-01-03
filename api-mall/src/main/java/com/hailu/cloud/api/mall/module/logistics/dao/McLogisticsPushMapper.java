package com.hailu.cloud.api.mall.module.logistics.dao;

import com.hailu.cloud.api.mall.module.logistics.entity.McLogisticsPush;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2020/1/3 0003 14:27
 */
@Mapper
public interface McLogisticsPushMapper {
    /**
     *
     * @mbggenerated 2020-01-02
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-01-02
     */
    int insertSelective(McLogisticsPush record);

    /**
     *
     * @mbggenerated 2020-01-02
     */
    McLogisticsPush selectByPrimaryKey(Long id);
}
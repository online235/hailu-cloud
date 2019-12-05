package com.hailu.cloud.api.admin.module.xinan.dao;

import com.hailu.cloud.api.admin.module.xinan.entity.CharitableCommonweal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/3 0003 14:56
 */
@Mapper
public interface CharitableCommonwealMapper {
    /**
     *
     * @mbggenerated 2019-12-02
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 添加公益
     * @mbggenerated 2019-12-02
     */
    int insertSelective(CharitableCommonweal record);


    /**
     * 根据编号更改信息
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKeySelective(CharitableCommonweal record);


    /**
     * 根据政府编号查询公益列表
     * @param adminId
     * @return
     */
    List<CharitableCommonweal> findCharitableCommonwealByAdminId(@Param("adminId") Long adminId);

    /**
     * 超级管理员获取公益列表
     * @return
     */
    List<CharitableCommonweal> findCharitableCommonwealList();

    /**
     * 根据编号查询详细信息
     * @param Id
     * @return
     */
    CharitableCommonweal findCharitableCommonwealById(@Param("Id") Long Id);
}
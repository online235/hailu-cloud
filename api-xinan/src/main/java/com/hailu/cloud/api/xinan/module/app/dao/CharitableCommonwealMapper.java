package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.CharitableCommonweal;
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
     * 根据政府编号查询公益列表
     * @param adminId
     * @return
     */
    List<CharitableCommonweal> findCharitableCommonwealByAdminId(@Param("adminId") Long adminId);

    /**
     * 根据编号查询详细信息
     * @param Id
     * @return
     */
    CharitableCommonweal findCharitableCommonwealById(@Param("Id") Long Id);
}
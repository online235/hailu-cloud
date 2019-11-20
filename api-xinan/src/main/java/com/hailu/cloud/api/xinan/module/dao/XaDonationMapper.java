package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.XaDonation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 11:39 2019/11/20 0020
 */
@Mapper
public interface XaDonationMapper {
    /**
     *
     * @mbggenerated 2019-10-22
     */
    int deleteByPrimaryKey(String numberid);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    int insert(XaDonation record);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    int insertSelective(XaDonation record);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    XaDonation selectByPrimaryKey(String numberid);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    int updateByPrimaryKeySelective(XaDonation record);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    int updateByPrimaryKey(XaDonation record);
}
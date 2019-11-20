package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.Donation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 11:39 2019/11/20 0020
 */
@Mapper
public interface DonationMapper {
    /**
     *
     * @mbggenerated 2019-10-22
     */
    int deleteByPrimaryKey(String numberid);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    int insert(Donation record);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    int insertSelective(Donation record);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    Donation selectByPrimaryKey(String numberid);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    int updateByPrimaryKeySelective(Donation record);

    /**
     *
     * @mbggenerated 2019-10-22
     */
    int updateByPrimaryKey(Donation record);
}
package com.hailu.cloud.api.merchant.module.app.dao;

import com.hailu.cloud.api.merchant.module.app.entity.McUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商户登陆Mapper
 * @Date: 16:32 2019/11/2 0002
 */
public interface McUserMapper {
    /**
     *
     * @mbggenerated 2019-10-23
     */
    int deleteByPrimaryKey(String numberid);

    /**
     *
     * @mbggenerated 2019-10-23
     */
    int insert(McUser record);

    /**
     *
     * @mbggenerated 2019-10-23
     */
    int insertSelective(McUser record);

    /**
     *
     * @mbggenerated 2019-10-23
     */
    McUser selectByPrimaryKey(String landingaccount);

    /**
     *
     * @mbggenerated 2019-10-23
     */
    McUser selectByPrimarykeyid(String numberid);

    /**
     *
     * @mbggenerated 2019-10-23
     */
    int updateByPrimaryKeySelective(McUser record);

    /**
     *
     * @mbggenerated 2019-10-23
     */
    int updateByPrimaryKey(McUser record);


    int selMcUserByPhone(String phone);

    /**
     * 查询输入的手机号码或者账号是否存在
     * @param random
     * @return
     */
    String selMcUserByPhoneAndLandingAccount(@Param("random") String random, @Param("numberId") String numberId);
}
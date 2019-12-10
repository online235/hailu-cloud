package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.ShopMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.Valid;


/**
 * @Author: QiuFeng:WANG
 * @Description: 心安登陆Mapper
 * @Date: 16:32 2019/11/2 0002
 */
@Mapper
public interface ShopMemberMapper {
    /**
     *
     * @mbggenerated 2019-10-19
     */
    int deleteByPrimaryKey(Integer memberId);

    /**
     *
     * @mbggenerated 2019-10-19
     */
    int insert(ShopMember record);

    /**
     *
     * @mbggenerated 2019-10-19
     */
    int insertSelective(ShopMember record);

    /**
     *
     * @mbggenerated 2019-10-19
     */
    ShopMember selectByPrimaryKey(String memberId);

    /**
     *
     * @param userId
     * @return
     */
    ShopMember selectByPrimaryByuserId(@Param("userId") String userId);

    /**
     *
     * @mbggenerated 2019-10-19
     */
    int updateByPrimaryKeySelective(ShopMember record);

    /**
     *
     * @mbggenerated 2019-10-19
     */
    int updateByPrimaryKeyWithBLOBs(ShopMember record);

    /**
     *
     * @mbggenerated 2019-10-19
     */
    int updateByPrimaryKey(ShopMember record);



    /**
     * 根据用户名查询个人信息
     * @param username
     * @return
     */
    ShopMember selectLitemallLogin(@Param("username") String username);


    /**
     * 添加用户
     * @param litemallUser
     * @return
     */
    int AddLitemallUser(ShopMember litemallUser);


    /**
     * 添加用户加密码
     * @param litemallUser
     * @return
     */
    int AddShopMember(ShopMember litemallUser);


    /**
     * 查询手机号码是否绑定
     * @param phone
     * @return
     */
    int selectLitemallByPhone(@Param("phone") String phone);



    /**
     * 手机号码查询数据
     * @param phone
     * @return
     */
    ShopMember findObjectByPhone(@Param("phone") String phone);


    int findShopMemberByUserIdAndMerchantType(String userId);
}
package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.ShopMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param userid
     * @return
     */
    ShopMember selectByPrimaryByuserId(String userid);

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
     * 根据用户名更新用户表token
     * @param token
     * @param time
     * @param username
     * @return
     */
    int addOrTokenLogin(@Param("token") String token, @Param("time") long time, @Param("username") String username);

    /**
     * 根据token跟新token是时间
     * @param token
     * @param time
     * @return
     */
    int updTokenCreate(@Param("token") String token, @Param("time") long time);

    /**
     * 根据用户名查询个人信息
     * @param username
     * @return
     */
    ShopMember selectLitemallLogin(@Param("username") String username);

    /**
     * 根据token查询个人信息
     * @param token
     * @return
     */
    ShopMember selectLitemallLoginToekn(@Param("token") String token);

    /**
     * 添加用户
     * @param litemallUser
     * @return
     */
    int AddLitemallUser(ShopMember litemallUser);

    /**
     * 查询手机号码是否绑定
     * @param phone
     * @return
     */
    int selectLitemallByPhone(@Param("phone") String phone);

    /**
     * 根据手机号码查询个人信息
     * @param phone
     * @return
     */
    ShopMember LoginPhone(@Param("phone") String phone);


    /**
     * 手机号码登陆
     * @param phone
     * @param token
     * @param time
     * @return
     */
    int updTokenLitemallUser(@Param("phone") String phone, @Param("token") String token, @Param("time") long time);

    /**
     * 退出登录
     * @param memberid
     * @return
     */
    int updOutLoginLitemallUser(@Param("memberid") String memberid);


    List<ShopMember> selectFindShopMember(String membername, String membermobile);
}
package com.hailu.cloud.api.mall.module.user.dao;


import com.hailu.cloud.api.mall.module.user.entity.InviteDetail;
import com.hailu.cloud.api.mall.module.user.vo.MemberDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InviteDetailMapper {
    /**
     *
     * @mbggenerated 2019-12-24
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-24
     */
    int insert(InviteDetail record);


    /**
     *
     * @mbggenerated 2019-12-24
     */
    InviteDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-24
     */
    int updateByPrimaryKeySelective(InviteDetail record);

    /**
     * 查询城市合伙人邀请列表
     * @param value
     * @param type
     * @param userId
     * @return
     */
    List<MemberDetailVo> findDetailByType(@Param("value") String value, @Param("type") Integer type, @Param("userId") String userId);

    List<MemberDetailVo> findMemberDetail(@Param("value") String value, @Param("type") Integer type, @Param("userId") String userId,@Param("memberAll") Integer memberAll);

    //TODO:暂时这样用，后期商家体系迁入会员体系再更改
    /**
     * 查询商家信息
     * @param value
     * @param userId
     * @return
     */
    List<MemberDetailVo> findMerchants(@Param("value") String value,@Param("userId") String userId);
}
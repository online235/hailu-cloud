package com.hailu.cloud.api.mall.module.user.dao;


import com.hailu.cloud.api.mall.module.user.entity.MemberDetail;
import com.hailu.cloud.api.mall.module.user.vo.MemberDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDetailMapper {
    /**
     *
     * @mbggenerated 2019-12-24
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-24
     */
    int insert(MemberDetail record);

    /**
     *
     * @param memberDetail
     * @return
     */
    int insertSelective(MemberDetail memberDetail);

    /**
     *
     * @mbggenerated 2019-12-24
     */
    MemberDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-24
     */
    int updateByPrimaryKeySelective(MemberDetail record);


    /**
     * 根据userId获取信息
     * @param userId
     * @return
     */
    MemberDetail findByUserId(@Param("userId") String userId);

    /**
     * 查询服务商
     * @param merchantType
     * @param userId
     * @return
     */
    List<MemberDetailVo> findServiceProvidersList(@Param("merchantType") Integer merchantType, @Param("userId")String userId);

}
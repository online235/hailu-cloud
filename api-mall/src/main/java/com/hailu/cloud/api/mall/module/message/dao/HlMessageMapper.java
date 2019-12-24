package com.hailu.cloud.api.mall.module.message.dao;


import com.hailu.cloud.api.mall.module.message.vo.HlMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HlMessageMapper {
    /**
     *
     * @mbggenerated 2019-12-27
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-27
     */
    int insertSelective(HlMessage record);

    /**
     *
     * @mbggenerated 2019-12-27
     */
    HlMessage selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-27
     */
    int updateByPrimaryKeySelective(HlMessage record);

    /**
     *
     * @param userId
     * @param messageStatus
     * @return
     */
    List<HlMessage> findList(@Param("userId") String userId, @Param("messageStatus")Integer messageStatus);

    /**
     * 更改消息状态
     * @param id
     * @param messageStatus
     */
   void updateMessageStatus(@Param("id") Long id,@Param("messageStatus") Integer messageStatus);

    /**
     * 获取未读取消息的数目
     * @param userId
     * @return
     */
   Integer getNoReadCount(@Param("userId") String userId);
}
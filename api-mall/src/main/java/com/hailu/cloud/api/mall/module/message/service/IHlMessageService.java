package com.hailu.cloud.api.mall.module.message.service;

/**
 * 用户信息
 * @author junpei.deng
 */
public interface IHlMessageService {

    /**
     * 分页查询数据
     * @param userId
     * @param page
     * @param size
     * @return
     */
    Object findListPage(String userId,Integer page,Integer size,Integer messageStatus);

    /**
     * 读取消息
     * @param id
     */
    void readMessage(Long id);

    /**
     * 获取未读消息的数量
     * @param userId
     * @return
     */
    Integer findNoReadCount(String userId);
}

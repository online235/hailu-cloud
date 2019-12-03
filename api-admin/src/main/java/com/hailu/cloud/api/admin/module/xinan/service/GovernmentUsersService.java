package com.hailu.cloud.api.admin.module.xinan.service;

import com.hailu.cloud.api.admin.module.xinan.entity.GovernmentUsers;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
public interface GovernmentUsersService {

    /**
     * 根据编号查询账号详细信息
     * @param id
     * @return
     */
    GovernmentUsers selectByPrimaryKey(Long id);

    /**
     * 添加政府账号
     * @mbggenerated 2019-12-02
     */
    GovernmentUsers insertSelective(GovernmentUsers record);
}

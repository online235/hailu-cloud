package com.hailu.cloud.api.xinan.module.app.service;

import com.hailu.cloud.api.xinan.module.app.entity.GovernmentUsers;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
public interface GovernmentUsersService {

    /**
     * 根据城市查询文章
     * @param cityCode
     * @return
     */
    GovernmentUsers findGovernmentUsersByCityCode(String cityCode);
}

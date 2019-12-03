package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.dao.GovernmentUsersMapper;
import com.hailu.cloud.api.xinan.module.app.entity.GovernmentUsers;
import com.hailu.cloud.api.xinan.module.app.service.GovernmentUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@Service
public class GovernmentUsersImpl implements GovernmentUsersService {

    @Resource
    private GovernmentUsersMapper governmentUsersMapper;

    @Override
    public GovernmentUsers findGovernmentUsersByCityCode(String cityCode) {
        return governmentUsersMapper.findGovernmentUsersByCityCode(cityCode);
    }
}

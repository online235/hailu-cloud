package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.dao.GovernmentMapper;
import com.hailu.cloud.api.xinan.module.app.model.GovernmentModel;
import com.hailu.cloud.api.xinan.module.app.service.GovernmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@Service
public class GovernmentImpl implements GovernmentService {

    @Resource
    private GovernmentMapper governmentUsersMapper;

    @Override
    public GovernmentModel findGovernmentUsersByCityCode(String cityCode) {
        return governmentUsersMapper.findGovernmentUsersByCityCode(cityCode);
    }
}

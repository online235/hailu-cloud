package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.hailu.cloud.api.admin.module.xinan.dao.GovernmentUsersMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.GovernmentUsers;
import com.hailu.cloud.api.admin.module.xinan.service.GovernmentUsersService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@Service
public class GovernmentUsersImpl implements GovernmentUsersService {

    @Resource
    private GovernmentUsersMapper governmentUsersMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public GovernmentUsers selectByPrimaryKey(Long id) {
        return governmentUsersMapper.selectByPrimaryKey(id);
    }

    @Override
    public GovernmentUsers insertSelective(GovernmentUsers record) {
        record.setId(basicFeignClient.uuid().getData());
        record.setCratedat(new Date());
        record.setUpdatedat(new Date());
        governmentUsersMapper.insertSelective(record);
        return record;

    }
}

package com.hailu.cloud.api.xinan.module.backstage.service.impl;

import com.hailu.cloud.api.xinan.module.backstage.dao.ManagementBackstageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagementBackstageService {

    @Resource
    private ManagementBackstageMapper managementBackstageMapper;

    public Object findLogin(String userName, String passWord){
        return managementBackstageMapper.findLogin(userName,passWord) > 0 ? true : false;
    }
}

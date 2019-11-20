package com.hailu.cloud.api.xinan.module.service;

import com.hailu.cloud.api.xinan.module.dao.XaManagementBackstageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class XaManagementBackstageService {

    @Resource
    private XaManagementBackstageMapper xaManagementBackstageMapper;

    public Object findLogin(String userName, String passWord){
        return xaManagementBackstageMapper.findLogin(userName,passWord) > 0 ? true : false;
    }
}

package com.hailu.cloud.api.basic.module.version.service.impl;

import com.hailu.cloud.api.basic.module.version.dao.VersionManagementMapper;
import com.hailu.cloud.api.basic.module.version.service.IVersionManagementService;
import com.hailu.cloud.common.model.basic.VersionManagement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 版本管理
 * @author 190726
 */
@Service
public class VersionManagementImpl implements IVersionManagementService {

    @Resource
    VersionManagementMapper versionManagementMapper;


    @Override
    public VersionManagement findByVersionAndType(String version, Integer type) {
        return versionManagementMapper.findByVersionAndType(version,type);
    }
}

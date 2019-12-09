package com.hailu.cloud.api.basic.module.version.service;

import com.hailu.cloud.common.model.basic.VersionManagement;

/**
 * 版本管理
 * @author 190726
 */
public interface IVersionManagementService {

    /**
     * 根据版本号和设备来源查询信息
     * @param version
     * @param type
     * @return
     */
    VersionManagement findByVersionAndType(String version,Integer type);

}

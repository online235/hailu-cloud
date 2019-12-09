package com.hailu.cloud.api.admin.module.system.service;

import com.github.pagehelper.Page;
import com.hailu.cloud.api.admin.module.system.model.VersionManagementModel;
import com.hailu.cloud.common.model.basic.VersionManagement;

/**
 * 版本管理
 * @author 190726
 */
public interface IVersionService {

    /**
     * 分页查找
     * @param version
     * @param type
     * @param page
     * @param size
     * @return
     */
    Object findPage(String version,Integer type,Integer page,Integer size);


    /**
     * 编辑或保存
     * @param versionManagementModel
     */
    void save(VersionManagementModel versionManagementModel);


    /**
     * 根据ID删除信息
     * @param id
     * @return
     */
    boolean delete(Long id);

}

package com.hailu.cloud.api.admin.module.system.dao;


import com.hailu.cloud.common.model.basic.VersionManagement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VersionManagementMapper {

    /**
     * 获取列表
     * @param type
     * @param version
     * @return
     */
    List<VersionManagement> findList(@Param("type") Integer type, @Param("version") String version);

    int add(VersionManagement versionManagement);

    int updateByPrimaryKeySelective(VersionManagement versionManagement);

    /**
     * 根据ID删除信息（伪删除）
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);
}

package com.hailu.cloud.api.basic.module.version.dao;

import com.hailu.cloud.common.model.basic.VersionManagement;
import org.apache.ibatis.annotations.Param;

public interface VersionManagementMapper {
    /**
     * 根据版本号和设备来源查询信息
     * @param version
     * @param type
     * @return
     */
    VersionManagement findByVersionAndType(@Param("version") String version, @Param("type")Integer type);

}

package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.ManagementType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ManagementTypeService {

    /**
     * 查询经营类型
     * @return
     */
    List<ManagementType> findManagementTypeList(Map<String, Object> parameter);


    /**
     * 根據id查询数据
     */
    ManagementType findManagementById(@Param("managementId")Long managementId);

}

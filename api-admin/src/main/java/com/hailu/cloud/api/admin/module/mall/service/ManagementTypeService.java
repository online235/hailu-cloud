package com.hailu.cloud.api.admin.module.mall.service;

import com.hailu.cloud.api.admin.module.mall.entity.ManagementType;
import com.hailu.cloud.api.admin.module.mall.model.ManagementTypeModel;
import com.hailu.cloud.common.exception.BusinessException;

import java.util.List;

public interface ManagementTypeService {

    /**
     * 添加营业类型
     * @mbggenerated 2019-11-25
     */
    ManagementTypeModel insertSelective(ManagementTypeModel record) throws BusinessException;

    /**
     * 查询父类型是否重复
     * @param managementName
     * @return
     */
    ManagementType findManagementType(String managementName);

    /**
     * 根据Id查询经营类型详细
     * @param managementId
     * @return
     */
    ManagementType findManagementTypeByManagementId(Long managementId);

    /**
     * 查询子类型是否重复
     * @param parentId
     * @param managementName
     * @return
     */
    ManagementType findManagementTypeByparentId(long parentId, String managementName);

    /**
     * 查询经营类型
     * @param parentId
     * @return
     */
    List<ManagementType> findManagementTypeList(long parentId);

    /**
     * 更改经验类型
     * @param managementTypeModel
     * @return
     */
    ManagementTypeModel updeteManagementTypeModel(ManagementTypeModel managementTypeModel);
}

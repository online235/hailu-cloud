package com.hailu.cloud.api.basic.module.mail.service;

import com.hailu.cloud.api.basic.module.mail.entity.ManagementType;
import com.hailu.cloud.api.basic.module.mail.model.ManagementTypeModel;
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

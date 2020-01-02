package com.hailu.cloud.api.admin.module.mall.service;

import com.hailu.cloud.api.admin.module.mall.entity.ManagementType;
import com.hailu.cloud.api.admin.module.mall.model.ManagementTypeModel;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;
import java.util.Map;

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
    ManagementTypeModel findManagementTypeByManagementId(Long managementId);

    /**
     * 查询子类型是否重复
     * @param parentId
     * @param managementName
     * @return
     */
    ManagementType findManagementTypeByparentId(long parentId, String managementName);

    /**
     * 查询经营类型
     * @param parameter
     * @return
     */
    PageInfoModel<List<ManagementTypeModel>> findManagementTypeList(Map<String, Object> parameter,Integer pageNum,Integer pageSize);

    /**
     * 更改经验类型
     * @param managementTypeModel
     * @return
     */
    ManagementTypeModel updeteManagementTypeModel(ManagementTypeModel managementTypeModel);
}

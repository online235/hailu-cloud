package com.hailu.cloud.api.admin.module.mall.service.impl;

import com.hailu.cloud.api.admin.module.mall.dao.ManagementTypeMapper;
import com.hailu.cloud.api.admin.module.mall.entity.ManagementType;
import com.hailu.cloud.api.admin.module.mall.model.ManagementTypeModel;
import com.hailu.cloud.api.admin.module.mall.service.ManagementTypeService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagementTypeImpl implements ManagementTypeService {

    @Resource
    private ManagementTypeMapper managementTypeMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public ManagementType findManagementType(String managementName) {
        return managementTypeMapper.findManagementType(managementName);
    }

    @Override
    public ManagementType findManagementTypeByManagementId(Long managementId) {
        return managementTypeMapper.findManagementTypeByManagementId(managementId);
    }

    @Override
    public ManagementType findManagementTypeByparentId(long parentId, String managementName) {
        return managementTypeMapper.findManagementTypeByparentId(parentId, managementName);
    }

    @Override
    public List<ManagementType> findManagementTypeList(long parentId) {
        return managementTypeMapper.findManagementTypeList(parentId);
    }

    @Override
    public ManagementTypeModel updeteManagementTypeModel(ManagementTypeModel managementTypeModel) {
        managementTypeMapper.updeteManagementTypeModel(managementTypeModel);
        return managementTypeModel;
    }


    @Override
    public ManagementTypeModel insertSelective(ManagementTypeModel record) throws BusinessException {
        ManagementType managementType;
        if (record.getParentId() == null){
            managementType = findManagementType(record.getManagementName());
            record.setParentId(Long.parseLong("0"));
        }else{
            managementType = findManagementTypeByparentId(record.getParentId(), record.getManagementName());
        }

        if (managementType != null){
            throw new BusinessException("行业已存在");
        }

        record.setManagementId(Long.parseLong(String.valueOf(basicFeignClient.uuid().getData())));
        managementTypeMapper.insertSelective(record);
        return record;
    }


}

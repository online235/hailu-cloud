package com.hailu.cloud.api.basic.module.mail.service.impl;

import com.hailu.cloud.api.basic.module.mail.dao.ManagementTypeMapper;
import com.hailu.cloud.api.basic.module.mail.entity.ManagementType;
import com.hailu.cloud.api.basic.module.mail.model.ManagementTypeModel;
import com.hailu.cloud.api.basic.module.mail.service.ManagementTypeService;
import com.hailu.cloud.api.basic.module.uid.component.UidGenerator;
import com.hailu.cloud.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagementTypeImpl implements ManagementTypeService {

    @Resource
    private ManagementTypeMapper managementTypeMapper;

    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public ManagementType findManagementType(String managementName) {
        return managementTypeMapper.findManagementType(managementName);
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

        record.setManagementId(uidGenerator.uuid());
        managementTypeMapper.insertSelective(record);
        return record;
    }


}

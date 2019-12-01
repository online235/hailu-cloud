package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.hailu.cloud.api.mall.module.multiindustry.dao.ManagementTypeMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.ManagementType;
import com.hailu.cloud.api.mall.module.multiindustry.service.ManagementTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagementTypeImpl implements ManagementTypeService {

    @Resource
    private ManagementTypeMapper managementTypeMapper;

    @Override
    public List<ManagementType> findManagementTypeList(long parentId) {
        return managementTypeMapper.findManagementTypeList(parentId);
    }


}
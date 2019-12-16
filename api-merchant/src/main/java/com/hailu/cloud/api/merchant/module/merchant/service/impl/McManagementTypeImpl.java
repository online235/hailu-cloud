package com.hailu.cloud.api.merchant.module.merchant.service.impl;


import com.hailu.cloud.api.merchant.module.merchant.dao.McManagementTypeDao;
import com.hailu.cloud.api.merchant.module.merchant.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.merchant.model.McManagementTypeVo;
import com.hailu.cloud.api.merchant.module.merchant.service.McManagementTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author zhangmugui
 */
@Service
public class McManagementTypeImpl implements McManagementTypeService {


    @Resource
    private McManagementTypeDao mcManagementTypeDao;

    @Override
    public List<McManagementType> findListByParam(Object parameter) {
        return mcManagementTypeDao.findListByParam(parameter);
    }

    @Override
    public McManagementType findObjectByParentName(String parentName) {
        return mcManagementTypeDao.findObjectByParentName(parentName);
    }


    @Override
    public List<McManagementTypeVo> findManagementTypeListResult(Object parameter) {
        return mcManagementTypeDao.findManagementTypeListResult(parameter);
    }

    @Override
    public McManagementType findManagementById(Long managementId) {
        return mcManagementTypeDao.findManagementById(managementId);
    }
}

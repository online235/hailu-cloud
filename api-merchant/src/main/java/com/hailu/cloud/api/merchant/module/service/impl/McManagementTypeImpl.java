package com.hailu.cloud.api.merchant.module.service.impl;

import com.hailu.cloud.api.merchant.module.dao.McManagementTypeDao;
import com.hailu.cloud.api.merchant.module.service.McManagementTypeService;
import com.hailu.cloud.api.merchant.module.entity.McManagementType;
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


}

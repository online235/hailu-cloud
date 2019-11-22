package com.hailu.cloud.api.merchant.module.lifecircle.service.impl;


import com.hailu.cloud.api.merchant.module.lifecircle.dao.McManagementTypeDao;
import com.hailu.cloud.api.merchant.module.lifecircle.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.lifecircle.model.McManagementTypeVo;
import com.hailu.cloud.api.merchant.module.lifecircle.service.McManagementTypeService;
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


}

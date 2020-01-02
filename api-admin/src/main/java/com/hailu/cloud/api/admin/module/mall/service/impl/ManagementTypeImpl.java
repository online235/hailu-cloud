package com.hailu.cloud.api.admin.module.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.mall.dao.ManagementTypeMapper;
import com.hailu.cloud.api.admin.module.mall.entity.ManagementType;
import com.hailu.cloud.api.admin.module.mall.model.ManagementTypeModel;
import com.hailu.cloud.api.admin.module.mall.service.ManagementTypeService;
import com.hailu.cloud.api.admin.module.merchant.model.McStoreInformationModel;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McStoreInformationListParameter;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public ManagementTypeModel findManagementTypeByManagementId(Long managementId) {
        return managementTypeMapper.findManagementTypeByManagementId(managementId);
    }

    @Override
    public ManagementType findManagementTypeByparentId(long parentId, String managementName) {
        return managementTypeMapper.findManagementTypeByparentId(parentId, managementName);
    }

    @Override
    public PageInfoModel<List<ManagementTypeModel>>  findManagementTypeList(Map<String, Object> parameter,Integer pageNum,Integer pageSize) {
        Page pageData = PageHelper.startPage(pageNum,pageSize);
        List<ManagementTypeModel> result =  managementTypeMapper.findManagementTypeList(parameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
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

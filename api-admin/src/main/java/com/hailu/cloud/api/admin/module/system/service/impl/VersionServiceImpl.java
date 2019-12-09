package com.hailu.cloud.api.admin.module.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.system.dao.VersionManagementMapper;
import com.hailu.cloud.api.admin.module.system.model.VersionManagementModel;
import com.hailu.cloud.api.admin.module.system.service.IVersionService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.basic.VersionManagement;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 版本管理
 * @author 190726
 */
@Service
public class VersionServiceImpl implements IVersionService {

    @Resource
    private VersionManagementMapper versionManagementMapper;

    @Resource
    private BasicFeignClient basicFeignClient;


    @Override
    public Object findPage(String version, Integer type, Integer page, Integer size) {

        Page pageData = PageHelper.startPage(page,size);
        List<VersionManagement> list = versionManagementMapper.findList(type,version);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), list);
    }


    @Override
    public void save(VersionManagementModel versionManagementModel) {
        VersionManagement versionManagement = new VersionManagement();
        BeanUtils.copyProperties(versionManagementModel,versionManagement);
        saveEntity(versionManagement);
    }

    private VersionManagement saveEntity(VersionManagement versionManagement){
        Date dateNow = new Date();
        versionManagement.setModifyTime(dateNow);
        String userId = String.valueOf(RequestUtils.getAdminLoginInfo().getId());
        versionManagement.setModifyBy(userId);
        if(versionManagement.getId() == null){
            versionManagement.setId(basicFeignClient.uuid().getData());
            versionManagement.setCreateBy(userId);
            versionManagement.setCreateTime(dateNow);
            versionManagement.setStatus(1);
            versionManagementMapper.add(versionManagement);
            return versionManagement;
        }
        versionManagementMapper.updateByPrimaryKeySelective(versionManagement);
        return versionManagement;
    }

    @Override
    public boolean delete(Long id) {
        return versionManagementMapper.deleteById(id) > 0;
    }
}

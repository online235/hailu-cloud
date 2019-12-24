package com.hailu.cloud.api.admin.module.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.mall.dao.ServiceProvidersMapper;
import com.hailu.cloud.api.admin.module.mall.entity.ServiceProviders;
import com.hailu.cloud.api.admin.module.mall.model.ServiceProvidersModel;
import com.hailu.cloud.api.admin.module.mall.service.IServiceProvidersService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author 190726
 */
@Service
public class ServiceProvidersServiceImpl implements IServiceProvidersService {

    @Resource
    private ServiceProvidersMapper serviceProvidersMapper;


    @Override
    public Object findListPage(Integer isService,String name, Integer pageNum, Integer size) {
        Page page = PageHelper.startPage(pageNum,size);
        List<ServiceProvidersModel> list = serviceProvidersMapper.findList(name,isService);
        return new PageInfoModel<>(page.getPages(), page.getTotal(), list);
    }


    @Override
    public ServiceProviders findById(Long id) {
        return serviceProvidersMapper.selectByPrimaryKey(id);
    }

    @Override
    public ServiceProviders edit(ServiceProviders serviceProviders) {
        serviceProvidersMapper.updateByPrimaryKeySelective(serviceProviders);
        return serviceProviders;
    }
}

package com.hailu.cloud.api.admin.module.mall.service;

import com.hailu.cloud.api.admin.module.mall.entity.ServiceProviders;

/**
 * @author 190726
 */
public interface IServiceProvidersService {


    /**
     * 查询分页信息
     * @param name
     * @param page
     * @param size
     * @return
     */
    Object findListPage(Integer isService,String name, Integer page, Integer size);


    /**
     * 根据ID查询消息
     * @param id
     * @return
     */
    ServiceProviders findById(Long id);

    /**
     * 保存或修改
     * @param serviceProviders
     * @return
     */
    ServiceProviders edit(ServiceProviders serviceProviders);
}

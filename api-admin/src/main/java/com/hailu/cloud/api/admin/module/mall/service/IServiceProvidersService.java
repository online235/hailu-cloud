package com.hailu.cloud.api.admin.module.mall.service;

import com.hailu.cloud.api.admin.module.mall.model.ServiceProvidersDto;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.models.auth.In;

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
     * 保存服务商信息
     * @param dto
     * @return
     */
    Long saveServiceProvider(ServiceProvidersDto dto) throws BusinessException;

    /**
     * 查询服务商信息
     * @return
     */
    ServiceProvidersDto findDetail();

    /**
     * 根据userId修改服务商状态
     * @param userId
     * @param isService
     * @return
     */
    int updateStatusByUserId(String userId, int isService);
}

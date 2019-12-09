package com.hailu.cloud.api.mall.module.serviceproviders.service;

import com.hailu.cloud.api.mall.module.serviceproviders.vo.ServiceProvidersDto;
import com.hailu.cloud.common.exception.BusinessException;

/**
 * @author 190726
 */
public interface IServiceProvidersService {

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
    int updateStatusByUserId(String userId,int isService);
}

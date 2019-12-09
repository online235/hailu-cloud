package com.hailu.cloud.api.mall.module.serviceproviders.service.impl;

import com.hailu.cloud.api.mall.module.serviceproviders.dao.ServiceProvidersMapper;
import com.hailu.cloud.api.mall.module.serviceproviders.entity.ServiceProviders;
import com.hailu.cloud.api.mall.module.serviceproviders.service.IServiceProvidersService;
import com.hailu.cloud.api.mall.module.serviceproviders.vo.ServiceProvidersDto;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 190726
 */
@Service
public class ServiceProvidersServiceImpl implements IServiceProvidersService {

    @Resource
    private ServiceProvidersMapper serviceProvidersMapper;

    @Resource
    private BasicFeignClient basicFeignClient;


    @Override
    public Long saveServiceProvider(ServiceProvidersDto dto) throws BusinessException {
        ServiceProviders serviceProviders = new ServiceProviders();
        BeanUtils.copyProperties(dto,serviceProviders);
        //是否服务商（1-是、2-否）
        serviceProviders.setIsService(2);
        return saveEntity(serviceProviders).getId();
    }


    /**
     * 新增或保存
     * @param serviceProviders
     * @return
     */
    private ServiceProviders saveEntity(ServiceProviders serviceProviders) throws BusinessException {
        Date dateNow = new Date();
        //修改时间
        serviceProviders.setModifyTime(dateNow);
        String userId = RequestUtils.getMemberLoginInfo().getUserId();
        //修改人
        serviceProviders.setModifyBy(userId);
        if(serviceProviders.getId() == null){

            //状态（1-正常、2-删除）
            serviceProviders.setStatus(1);
            //用户ID
            serviceProviders.setUserId(userId);
            if(existsPhone(serviceProviders.getPhone())){
                throw new BusinessException("该手机号码已存在！");
            }
            if(existsUserId(userId)){
                throw new BusinessException("您已申请服务商，不可再次申请！");
            }
            //ID
            serviceProviders.setId(basicFeignClient.uuid().getData());
            //创建人
            serviceProviders.setCreateBy(userId);
            //创建时间
            serviceProviders.setCreateTime(dateNow);

            serviceProvidersMapper.insert(serviceProviders);
            return serviceProviders;
        }
        serviceProvidersMapper.updateByPrimaryKeySelective(serviceProviders);
        return serviceProviders;
    }


    /**
     * 校验手机号码是否存在
     * @param phone
     * @return 有返回 true，没有返回false
     */
    private boolean existsPhone(String phone){
        if(StringUtils.isBlank(phone)){
            return false;
        }
        return serviceProvidersMapper.findByPhone(phone) != null;
    }

    /**
     * 校验用户iD是否存在
     * @param userId
     * @return 有返回 true，没有返回false
     */
    private boolean existsUserId(String userId){
        if(StringUtils.isBlank(userId)){
            return false;
        }
        return serviceProvidersMapper.findByUserId(userId) != null;
    }


    @Override
    public ServiceProvidersDto findDetail() {
        String userId = RequestUtils.getMemberLoginInfo().getUserId();
        ServiceProvidersDto serviceProvidersDto = new ServiceProvidersDto();
        BeanUtils.copyProperties(serviceProvidersMapper.findByUserId(userId),serviceProvidersDto);
        return serviceProvidersDto;
    }

    @Override
    public int updateStatusByUserId(String userId, int isService) {
        return serviceProvidersMapper.updateStatusByUserId(userId,isService);
    }
}

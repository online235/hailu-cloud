package com.hailu.cloud.api.admin.module.mall.dao;

import com.hailu.cloud.api.admin.module.mall.entity.ServiceProviders;
import com.hailu.cloud.api.admin.module.mall.model.ServiceProvidersDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 190726
 */
public interface ServiceProvidersMapper {

    /**
     *
     * 根据ID删除
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  保存
     * @mbggenerated 2019-12-05
     */
    int insert(ServiceProviders record);

    /**
     *  根据ID查询信息
     * @mbggenerated 2019-12-05
     */
    ServiceProviders selectByPrimaryKey(Long id);

    /**
     *  修改信息
     * @mbggenerated 2019-12-05
     */
    int updateByPrimaryKeySelective(ServiceProviders record);

    /**
     * 根据手机号码查询信息
     * @param phone
     * @return
     */
    ServiceProviders findByPhone(@Param("phone") String phone);

    /**
     * 根据用户ID查询信息
     * @param userId
     * @return
     */
    ServiceProviders findByUserId(@Param("userId") String userId);

    /**
     * 根据userId修改服务商状态
     * @param userId
     * @param isService
     * @return
     */
    int updateStatusByUserId(@Param("userId") String userId, @Param("isService") int isService);

    /**
     * 查询列表
     * @param name
     * @param isService
     * @return
     */
    List<ServiceProvidersDto> findList(@Param("name") String name,@Param("isService") Integer isService);
}

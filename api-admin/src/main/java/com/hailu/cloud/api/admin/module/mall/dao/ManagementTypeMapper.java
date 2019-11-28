package com.hailu.cloud.api.admin.module.mall.dao;

import com.hailu.cloud.api.admin.module.mall.entity.ManagementType;
import com.hailu.cloud.api.admin.module.mall.model.ManagementTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 经营类型
 * @Date: 15:17 2019/11/25 0025
 */
@Mapper
public interface ManagementTypeMapper {

    /**
     * 添加经营类型
     * @mbggenerated 2019-11-25
     */
    int insertSelective(ManagementTypeModel record);

    /**
     * 查询父类型是否重复
     * @param managementName
     * @return
     */
    ManagementType findManagementType(@Param("managementName") String managementName);

    /**
     * 根据Id查询经营类型详细
     * @param managementId
     * @return
     */
    ManagementType findManagementTypeByManagementId(@Param("managementId") Long managementId);

    /**
     * 查询子类型是否重复
     * @param parentId
     * @param managementName
     * @return
     */
    ManagementType findManagementTypeByparentId(@Param("parentId") long parentId, @Param("managementName") String managementName);

    /**
     * 根据父级Id查询经营类型
     * @param parentId
     * @return
     */
    List<ManagementType> findManagementTypeList(@Param("parentId") long parentId);

    /**
     * 更改经验类型
     * @param managementTypeModel
     * @return
     */
    int updeteManagementTypeModel(ManagementTypeModel managementTypeModel);
}
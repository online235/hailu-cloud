package com.hailu.cloud.api.basic.module.mail.dao;

import com.hailu.cloud.api.basic.module.mail.entity.ManagementType;
import com.hailu.cloud.api.basic.module.mail.model.ManagementTypeModel;
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
     * 查询子类型是否重复
     * @param parentId
     * @param managementName
     * @return
     */
    ManagementType findManagementTypeByparentId(@Param("parentId")long parentId, @Param("managementName") String managementName);

    List<ManagementType> findManagementTypeList(@Param("parentId") long parentId);
}
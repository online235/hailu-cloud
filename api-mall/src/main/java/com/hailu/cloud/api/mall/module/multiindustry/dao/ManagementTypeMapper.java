package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.ManagementType;
import com.hailu.cloud.api.mall.module.multiindustry.model.ManagementTypeModel;
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
     * 根据父级Id查询经营类型
     * @param parentId
     * @return
     */
    List<ManagementType> findManagementTypeList(@Param("parentId") long parentId);
}
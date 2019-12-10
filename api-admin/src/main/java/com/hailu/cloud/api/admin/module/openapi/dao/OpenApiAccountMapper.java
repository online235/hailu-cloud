package com.hailu.cloud.api.admin.module.openapi.dao;

import com.hailu.cloud.common.model.system.OpenApiAccountModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuzhijie
 */
@Mapper
public interface OpenApiAccountMapper {

    /**
     * 添加账号
     *
     * @param model
     */
    void addAccount(OpenApiAccountModel model);

    /**
     * 删除账号
     *
     * @param id
     */
    void delAccount(@Param("id") Long id);

    /**
     * 查询账号列表
     *
     * @param companyName 公司名称
     * @return
     */
    List<OpenApiAccountModel> list(@Param("companyName") String companyName);

}

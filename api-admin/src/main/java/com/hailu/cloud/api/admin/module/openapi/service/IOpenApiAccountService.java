package com.hailu.cloud.api.admin.module.openapi.service;

import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.OpenApiAccountModel;

import java.util.List;

/**
 * @author xuzhijie
 */
public interface IOpenApiAccountService {

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
    void delAccount(Long id);

    /**
     * 查询账号列表
     *
     * @param companyName 公司名称
     * @param pageNum     当前页
     * @param pageSize    每页显示数量
     * @return
     */
    PageInfoModel<List<OpenApiAccountModel>> list(String companyName, int pageNum, int pageSize);

}

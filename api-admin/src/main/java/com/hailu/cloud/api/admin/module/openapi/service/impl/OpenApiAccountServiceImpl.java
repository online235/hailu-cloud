package com.hailu.cloud.api.admin.module.openapi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.openapi.dao.OpenApiAccountMapper;
import com.hailu.cloud.api.admin.module.openapi.service.IOpenApiAccountService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.security.OpenAuthInfoModel;
import com.hailu.cloud.common.model.system.OpenApiAccountModel;
import com.hailu.cloud.common.utils.OpenAuthInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuzhijie
 */
@Slf4j
@Service
public class OpenApiAccountServiceImpl implements IOpenApiAccountService {

    @Resource
    private OpenApiAccountMapper openApiAccountMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public void addAccount(OpenApiAccountModel model) {
        long time = System.currentTimeMillis();
        OpenAuthInfoModel authInfoModel = OpenAuthInfoUtils.create(time);
        model.setId(basicFeignClient.uuid().getData());
        model.setAppId(authInfoModel.getAppId());
        model.setAppSecret(authInfoModel.getAppSecret());
        model.setTime(time);
        openApiAccountMapper.addAccount(model);
    }

    @Override
    public void delAccount(Long id) {
        openApiAccountMapper.delAccount(id);
    }

    @Override
    public PageInfoModel<List<OpenApiAccountModel>> list(String companyName, int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<OpenApiAccountModel> datas = openApiAccountMapper.list(companyName);
        return new PageInfoModel<>(page.getPages(), page.getTotal(), datas);
    }
}

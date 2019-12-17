package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.xinan.module.app.dao.SysBannerMapper;
import com.hailu.cloud.api.xinan.module.app.model.BannerResult;
import com.hailu.cloud.api.xinan.module.app.model.SysBannerModel;
import com.hailu.cloud.api.xinan.module.app.service.SysBannerService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysBannerServiceImpl implements SysBannerService {


    @Autowired
    private SysBannerMapper sysBannerMapper;


    @Override
    public List<BannerResult> findListByParameter(Object parameter) {
        return sysBannerMapper.findListByParameter(parameter);
    }

    @Override
    public SysBannerModel findSysBannerModelById(Long id) {
        return sysBannerMapper.findSysBannerModelById(id);
    }


    /**
     * 查询信息列表分页
     */
    @Override
    public PageInfoModel<List<BannerResult>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter) {

        Page pageData = PageHelper.startPage(pageNum, size);
        List<BannerResult> result = sysBannerMapper.findListByParameter(parameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }


}

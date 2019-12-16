package com.hailu.cloud.api.admin.module.banner.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.banner.dao.SysBannerMapper;
import com.hailu.cloud.api.admin.module.banner.entity.SysBanner;
import com.hailu.cloud.api.admin.module.banner.model.SysBannerModel;
import com.hailu.cloud.api.admin.module.banner.service.SysBannerService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysBannerServiceImpl implements SysBannerService {


    @Autowired
    private SysBannerMapper sysBannerMapper;
    @Autowired
    private BasicFeignClient uuidFeign;


    @Override
    public List<SysBannerModel> findListByParameter(Object parameter) {
        return sysBannerMapper.findListByParameter(parameter);
    }

    @Override
    public SysBannerModel findSysBannerModelById(Long id) {
        return sysBannerMapper.findSysBannerModelById(id);
    }

    @Override
    public int insertBannerModel(SysBanner sysBanner) {

        sysBanner.setId(uuidFeign.uuid().getData());
        sysBanner.setCreateTime(new Date());
        sysBanner.setUpdateTime(new Date());
        return sysBannerMapper.insertBannerModel(sysBanner);
    }

    @Override
    public int update(SysBanner sysBanner) {

        sysBanner.setUpdateTime(new Date());
        return sysBannerMapper.update(sysBanner);
    }

    /**
     * 查询信息列表分页
     */
    @Override
    public PageInfoModel<List<SysBannerModel>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter) {

        Page pageData = PageHelper.startPage(pageNum, size);
        List<SysBannerModel> result = sysBannerMapper.findListByParameter(parameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }


}

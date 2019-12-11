package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.xinan.module.app.dao.XaStatisticsMapper;
import com.hailu.cloud.api.xinan.module.app.entity.XaStatistics;
import com.hailu.cloud.api.xinan.module.app.model.XaStatisticsModel;
import com.hailu.cloud.api.xinan.module.app.service.XaStatisticsService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class XaStatisticsServiceImpl implements XaStatisticsService {

    @Autowired
    private XaStatisticsMapper xaStatisticsMapper;

    @Autowired
    private BasicFeignClient uuidFeign;


    @Override
    public List<XaStatisticsModel> findListByParameter(Object parameter) {
        return xaStatisticsMapper.findListByParameter(parameter);
    }

//
//    @Override
//    public int insert(XaStatistics xaStatistics) {
//        xaStatistics.setId(uuidFeign.uuid().getData());
//        xaStatistics.setCreateTime(new Date());
//        xaStatistics.setUpdateTime(new Date());
//        return xaStatisticsMapper.insert(xaStatistics);
//    }
//
//    @Override
//    public int update(XaStatistics xaStatistics) {
//        xaStatistics.setUpdateTime(new Date());
//        return xaStatisticsMapper.update(xaStatistics);
//    }


    /**
     * 查询信息列表分页
     */
    @Override
    public PageInfoModel<List<XaStatisticsModel>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter) {

        Page pageData = PageHelper.startPage(pageNum, size);
        List<XaStatisticsModel> result = xaStatisticsMapper.findListByParameter(parameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }


}

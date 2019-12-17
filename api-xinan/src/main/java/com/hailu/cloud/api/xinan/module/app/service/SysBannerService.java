package com.hailu.cloud.api.xinan.module.app.service;

import com.hailu.cloud.api.xinan.module.app.entity.SysBanner;
import com.hailu.cloud.api.xinan.module.app.model.BannerResult;
import com.hailu.cloud.api.xinan.module.app.model.SysBannerModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysBannerService {


    /**
     * 根据条件查询列表
     * @param parameter
     * @return
     */
    List<BannerResult> findListByParameter(Object parameter);


    /**
     * 根据id查数据
     * @param id
     * @return
     */
    SysBannerModel findSysBannerModelById(@Param("id") Long id);



    /**
     * 分页操作
     * @param pageNum
     * @param size
     * @param parameter
     * @return
     */
    PageInfoModel<List<BannerResult>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter);



}

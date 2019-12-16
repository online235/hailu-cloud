package com.hailu.cloud.api.admin.module.banner.service;


import com.hailu.cloud.api.admin.module.banner.entity.SysBanner;
import com.hailu.cloud.api.admin.module.banner.model.SysBannerModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SysBannerService {


    /**
     * 根据条件查询列表
     * @param parameter
     * @return
     */
    List<SysBannerModel> findListByParameter(Object parameter);


    /**
     * 根据id查数据
     * @param id
     * @return
     */
    SysBannerModel findSysBannerModelById(@Param("id") Long id);


    /**
     * 插入数据
     * @param sysBanner
     * @return
     */
    int insertBannerModel(SysBanner sysBanner);


    /**
     * 更新数据
     */
    int update(SysBanner sysBanner);


    /**
     * 分页操作
     * @param pageNum
     * @param size
     * @param parameter
     * @return
     */
    PageInfoModel<List<SysBannerModel>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter);



}

package com.hailu.cloud.api.admin.module.banner.dao;


import com.hailu.cloud.api.admin.module.banner.entity.SysBanner;
import com.hailu.cloud.api.admin.module.banner.model.SysBannerModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface SysBannerMapper {


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


}

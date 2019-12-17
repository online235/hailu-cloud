package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.model.BannerResult;
import com.hailu.cloud.api.xinan.module.app.model.SysBannerModel;
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
    List<BannerResult> findListByParameter(Object parameter);


    /**
     * 根据id查数据
     * @param id
     * @return
     */
    SysBannerModel findSysBannerModelById(@Param("id") Long id);



}

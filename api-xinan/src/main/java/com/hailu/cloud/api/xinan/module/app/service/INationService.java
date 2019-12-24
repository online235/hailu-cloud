package com.hailu.cloud.api.xinan.module.app.service;


import com.hailu.cloud.api.xinan.module.app.entity.Nation;

import java.util.List;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service.Impl
 * @Author: junpei.deng
 * @CreateTime: 2019-10-30 11:15
 * @Description: 省市区
 */
public interface INationService {

    /**
     * 根据ID获取省市区地址，不传默认查询省
     * @param parentCode
     * @return
     */
    Object findListByParentCode(String parentCode);

    /**
     * 根据区code查询父code
     * @param Code
     * @return
     */
    String findCodeBySonCode(String Code);



    /**
     * 根据省名称查找该数据
     * @param areaName
     */
    Nation findNationByAreaName(String areaName);




    /**
     * 根据code的集合查找list数据
     * @param parameter
     * @return
     */
    List<Nation> findListByCodeArray(Object parameter);



    Object findParentListByCode(String code);
}

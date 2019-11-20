package com.hailu.cloud.api.merchant.module.service;


import com.hailu.cloud.api.merchant.module.entity.McManagementType;

import java.util.List;

/**
 * @Author: zhangmugui
 * @Description: 经营类型
 * @Date: 2019.11.19
 */
public interface McManagementTypeService {

    /**
     * 根据参数搜索list数据
     * @param parameter
     * @return
     */
    List<McManagementType> findListByParam(Object parameter);

    /**
     * 根据父类名称搜索父类数据
     * @param parentName
     * @return
     */
    McManagementType findObjectByParentName(String parentName);

}

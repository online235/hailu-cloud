package com.hailu.cloud.api.merchant.module.lifecircle.service;




import com.hailu.cloud.api.merchant.module.lifecircle.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.lifecircle.model.McManagementTypeVo;

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


    /**
     * 根据id搜索数据
     * @param managementId
     * @return
     */
    McManagementType findManagementById(Long managementId);


    /**
     * 根据父类下子类
     * @param parameter
     * @return
     */
    List<McManagementTypeVo> findManagementTypeListResult(Object parameter);

}

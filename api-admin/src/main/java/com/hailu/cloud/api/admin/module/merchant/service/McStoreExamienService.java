package com.hailu.cloud.api.admin.module.merchant.service;


import com.hailu.cloud.api.admin.module.merchant.entity.McStoreExamine;
import com.hailu.cloud.api.admin.module.merchant.model.McStoreExamineModel;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McStoreExamineListParameter;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

public interface McStoreExamienService {



    /**
     * 更新数据
     * @param mcStoreExamine
     * @return
     */
    int updateByPrimaryKey(McStoreExamine mcStoreExamine);


    /**
     * 根据参数查数据
     * @param parameter
     * @return
     */
    List<McStoreExamineModel> findListByParam(Object parameter);


    /**
     * 根据id查数据
     * @param id
     * @return
     */
    McStoreExamine findObjectById(Long id);


    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 分页
     * @return
     */
    PageInfoModel<List<McStoreExamineModel>> selectMcStoreExamineModelList(McStoreExamineListParameter mcStoreExamineListParameter);

    /**
     * 电话审核状态
     * @param id
     * @param phoneToExamine
     * @throws BusinessException
     */
    void updatePhoneToExamine(Long id,Integer phoneToExamine) throws BusinessException;


    /**
     * 地址审核状态
     * @param id
     * @param addressToExamine
     * @throws BusinessException
     */
    void updateAddressToExamine(Long id,Integer addressToExamine) throws BusinessException;

}

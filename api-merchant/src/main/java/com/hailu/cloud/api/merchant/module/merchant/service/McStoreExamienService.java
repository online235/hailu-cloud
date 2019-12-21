package com.hailu.cloud.api.merchant.module.merchant.service;

import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreExamine;
import com.hailu.cloud.api.merchant.module.merchant.model.McStoreExamineModel;
import com.hailu.cloud.api.merchant.module.merchant.parameter.McStoreExamineParameter;
import com.hailu.cloud.api.merchant.module.merchant.result.ExamineResult;
import com.hailu.cloud.api.merchant.module.merchant.result.ShopExamineResult;
import com.hailu.cloud.common.exception.BusinessException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McStoreExamienService {

    /**
     * 插入数据
     *
     * @param mcStoreExamine
     * @return
     */
    int insertSelective(McStoreExamine mcStoreExamine);


    /**
     * 更新数据
     *
     * @param mcStoreExamine
     * @return
     */
    int updateByPrimaryKey(McStoreExamine mcStoreExamine);


    /**
     * 根据参数查数据
     *
     * @param parameter
     * @return
     */
    List<McStoreExamineModel> findListByParam(Object parameter);


    /**
     * 根据id查数据
     *
     * @param id
     * @return
     */
    McStoreExamineModel findObjectById(Long id);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据店铺id查数据
     *
     * @param storeId
     * @return
     */
    McStoreExamine findObjectByStoreId(@Param("storeId") Long storeId);


    /**
     * 提交更新审核信息
     * @param mcStoreExamineParameter
     * @throws BusinessException
     */
    void submitStoreExamine(McStoreExamineParameter mcStoreExamineParameter) throws BusinessException;

    /**
     * 获取地址电话是否在审核中状态,同时返回原来数据
     */
    ExamineResult getExamineResult(Long storeId) throws BusinessException;



}

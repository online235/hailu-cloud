package com.hailu.cloud.api.merchant.module.merchant.dao;


import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreExamine;
import com.hailu.cloud.api.merchant.module.merchant.model.McStoreExamineModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface McStoreExamineMapper {


    /**
     * 插入数据
     * @param mcStoreExamine
     * @return
     */
    int insertSelective(McStoreExamine mcStoreExamine);


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
    McStoreExamineModel findObjectById(@Param("storeId")Long id);


    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Long id);


    /**
     * 根据店铺id查数据
     * @param storeId
     * @return
     */
    McStoreExamine findObjectByStoreId(@Param("storeId") Long storeId);


}

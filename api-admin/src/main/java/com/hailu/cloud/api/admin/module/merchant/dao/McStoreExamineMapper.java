package com.hailu.cloud.api.admin.module.merchant.dao;




import com.hailu.cloud.api.admin.module.merchant.entity.McStoreExamine;
import com.hailu.cloud.api.admin.module.merchant.model.McStoreExamineModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface McStoreExamineMapper {




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
    McStoreExamine findObjectById(@Param("id") Long id);


    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(@Param("id")Long id);


}

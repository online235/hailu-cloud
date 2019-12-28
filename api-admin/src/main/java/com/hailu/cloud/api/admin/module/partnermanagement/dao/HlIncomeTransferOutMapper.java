package com.hailu.cloud.api.admin.module.partnermanagement.dao;

import com.hailu.cloud.api.admin.module.partnermanagement.model.HlIncomeTransferOutListModel;
import com.hailu.cloud.api.admin.module.partnermanagement.model.HlIncomeTransferOutModel;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HlIncomeTransferOutMapper {


    /**
     * 查列表
     * @param map
     * @return
     */
    List<HlIncomeTransferOutListModel> findListByParameter(Map<String,Object> map);


    /**
     * id查数据
     * @param id
     * @return
     */
    HlIncomeTransferOutModel findById(@ApiParam("id") Long id);


    /**
     * 审核
     * @param id       提现记录ID
     * @param updateBy 审核人ID
     * @param state    审核状态
     * @param remark   原因
     */
    void update(
            @Param("id") Long id,
            @Param("updateBy") String updateBy,
            @Param("state") Integer state,
            @Param("remark") String remark);


}

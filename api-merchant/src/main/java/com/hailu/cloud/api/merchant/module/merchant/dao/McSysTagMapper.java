package com.hailu.cloud.api.merchant.module.merchant.dao;


import com.hailu.cloud.api.merchant.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.merchant.module.merchant.result.McSysTagResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McSysTagMapper {

    /**
     * 查询标签列表
     * @return
     */
    List<McSysTag> findMcSysTagList();


    /**
     * 返回所有标签
     */
    List<McSysTagResult> findAllTagByStore();


}
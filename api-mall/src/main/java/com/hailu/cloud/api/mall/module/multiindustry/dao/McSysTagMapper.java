package com.hailu.cloud.api.mall.module.multiindustry.dao;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McSysTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McSysTagMapper {

    /**
     * 查询标签列表
     * @return
     */
    List<McSysTag> findMcSysTagList(@Param("tagName") String tagName);
}
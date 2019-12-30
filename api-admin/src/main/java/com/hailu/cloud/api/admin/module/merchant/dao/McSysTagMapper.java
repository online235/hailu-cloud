package com.hailu.cloud.api.admin.module.merchant.dao;


import com.hailu.cloud.api.admin.module.merchant.entity.McSysTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McSysTagMapper {
    /**
     *
     * @mbggenerated 2019-12-16
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加一个标签
     * @mbggenerated 2019-12-16
     */
    int insertSelective(McSysTag record);

    /**
     * 根据编号查询一个标签
     * @mbggenerated 2019-12-16
     */
    McSysTag selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-16
     */
    int updateByPrimaryKeySelective(McSysTag record);

    /**
     * 查询标签列表
     * @return
     */
    List<McSysTag> findMcSysTagList(@Param("tagName") String tagName);

    /**
     * 查询改标签是否存在
     * @param tagName
     * @return
     */
    int findMcSysTagByTagName(@Param("tagName") String tagName, @Param("tagType") Integer tagType);
}
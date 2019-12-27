package com.hailu.cloud.api.admin.module.merchant.service;

import com.hailu.cloud.api.admin.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McSysTagParameter;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 标签
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 16:
 */
public interface McSysTagService {

    /**
     * 添加一个标签
     * @return
     */
    McSysTag insertSelective(McSysTag mcSysTag);

    /**
     * 根据编号查询一个标签
     * @param id
     * @return
     */
    McSysTag selectByPrimaryKey(Long id);

    /**
     * 修改标签属性
     * @param record
     * @return
     */
    McSysTag updateByPrimaryKeySelective(McSysTagParameter record);

    /**
     * 删除一个标签
     * @param id
     * @return
     */
    void deleteByPrimaryKey(Long id , Integer deleteType);

    /**
     * 查询标签列表
     * @return
     */
    PageInfoModel<List<McSysTag>> findMcSysTagList(String tagName, Integer page, Integer size);
}

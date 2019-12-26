package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.McSysTag;
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
     * 查询标签列表
     * @return
     */
    PageInfoModel<List<McSysTag>> findMcSysTagList(String tagName, Integer page, Integer size);
}

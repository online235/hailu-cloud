package com.hailu.cloud.api.merchant.module.merchant.service;

import com.hailu.cloud.api.merchant.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.merchant.module.merchant.result.McSysTagResult;
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
    PageInfoModel<List<McSysTag>> findMcSysTagList(Integer page, Integer size);


    /**
     * 获取店铺可选择标签，同时返回可使用的标签
     */
    List<McSysTagResult> findAllTagByStore(Long storeId);




}

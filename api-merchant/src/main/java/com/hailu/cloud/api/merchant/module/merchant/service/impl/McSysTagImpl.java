package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.merchant.module.merchant.dao.McSysTagMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.merchant.module.merchant.service.McSysTagService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 标签Impl
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 16:
 */
@Service
public class McSysTagImpl implements McSysTagService {

    @Resource
    private McSysTagMapper mcSysTagMapper;

    @Override
    public PageInfoModel<List<McSysTag>> findMcSysTagList(Integer page, Integer size) {
        Page pageData = PageHelper.startPage(page, size);
        List<McSysTag> mcSysTags = mcSysTagMapper.findMcSysTagList();
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), mcSysTags);
    }

}

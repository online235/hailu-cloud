package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.McSysTagMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McSysTag;
import com.hailu.cloud.api.mall.module.multiindustry.service.McSysTagService;
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
    public PageInfoModel<List<McSysTag>> findMcSysTagList(String tagName, Integer page, Integer size) {
        Page pageData = PageHelper.startPage(page, size);
        List<McSysTag> mcSysTags = mcSysTagMapper.findMcSysTagList(tagName);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), mcSysTags);
    }


}

package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.merchant.module.merchant.dao.McSysTagMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.merchant.module.merchant.model.McShopTagModel;
import com.hailu.cloud.api.merchant.module.merchant.result.McSysTagResult;
import com.hailu.cloud.api.merchant.module.merchant.service.McShopTagService;
import com.hailu.cloud.api.merchant.module.merchant.service.McSysTagService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Iterator;
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
    @Resource
    private McShopTagService mcShopTagService;

    @Override
    public PageInfoModel<List<McSysTag>> findMcSysTagList(Integer page, Integer size) {
        Page pageData = PageHelper.startPage(page, size);
        List<McSysTag> mcSysTags = mcSysTagMapper.findMcSysTagList();
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), mcSysTags);
    }


    /**
     * 获取店铺可选择标签，同时返回可使用的标签
     */
    @Override
    public List<McSysTagResult> findAllTagByStore(Long storeId) {

        List<McSysTagResult> mcSysTagResultList = mcSysTagMapper.findAllTagByStore();
        List<McShopTagModel> mcShopTagModelList = mcShopTagService.findMcShopTagModelListByStoreId(storeId);
        if(!CollectionUtils.isEmpty(mcSysTagResultList)){
            Iterator iterator = mcSysTagResultList.iterator();
            while (iterator.hasNext()){
                McSysTagResult mcSysTagResult = (McSysTagResult)iterator.next();
                mcSysTagResult.setStoreUseState(2);
                if(!CollectionUtils.isEmpty(mcShopTagModelList)){
                    if(mcShopTagModelList.stream().anyMatch(mcShopTagModel -> mcShopTagModel.getTagId().equals(mcSysTagResult.getId()))){
                        mcSysTagResult.setStoreUseState(1);
                    }
                }
            }
        }
        return mcSysTagResultList;
    }


}

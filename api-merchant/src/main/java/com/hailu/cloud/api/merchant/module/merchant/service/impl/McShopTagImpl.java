package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.McShopTagMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McShopTag;
import com.hailu.cloud.api.merchant.module.merchant.model.McShopTagModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McShopTagService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺标签Impl
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 15:
 */
@Service
public class McShopTagImpl implements McShopTagService {

    @Resource
    private McShopTagMapper mcShopTagMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public List<McShopTag> findMcShopTagListByStoreId(Long storeId) {
        return mcShopTagMapper.findMcShopTagListByStoreId(storeId);
    }

    @Override
    public List<McShopTagModel> findMcShopTagModelListByStoreId(Long storeId) {
        return mcShopTagMapper.findMcShopTagModelListByStoreId(storeId);
    }

    @Override
    public void addOrUpdateMcSHopTag(Long[] tagId, Long storeId) {
        addOrUpdateModify(tagId, storeId);
    }

//    @Override
//    public List<McShopTag> updateMcShopTag(Long[] tagId, Long storeId) {
//        return addOrModify(tagId, storeId);
//    }

    @Override
    public int updateMcShopTagByStoreId(Long storeId) {
        return mcShopTagMapper.updateMcShopTagByStoreId(storeId);
    }

    @Override
    public int findMcShopTagByIdAndStoreId(Long tagId, Long storeId) {
        return mcShopTagMapper.findMcShopTagByIdAndStoreId(tagId, storeId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdateModify(Long[] tagId, Long storeId) {
        updateMcShopTagByStoreId(storeId);
        List<McShopTag> mcShopTags = findMcShopTagListByStoreId(storeId);
        List<McShopTag> addMcShopTag = new ArrayList<>();
        List<Long> idList = new ArrayList<>();
        //过滤不存在的数据
        List<McShopTag> mcShopTagIds = mcShopTags.stream().filter(mcShopTag -> Arrays.asList(tagId).contains(mcShopTag.getTagId())).collect(Collectors.toList());
        for (Long id : tagId) {
            if (mcShopTags.stream().allMatch(mcShopTag -> !mcShopTag.getTagId().equals(id))) {
                McShopTag addShopTag = new McShopTag();
                addShopTag.setId(basicFeignClient.uuid().getData());
                addShopTag.setStoreId(storeId);
                addShopTag.setTagId(id);
                addShopTag.setState(1);
                addMcShopTag.add(addShopTag);
            }
        }
        if (!CollectionUtils.isEmpty(addMcShopTag)) {
            mcShopTagMapper.addMcShopTag(addMcShopTag);
        }
        if (!CollectionUtils.isEmpty(mcShopTagIds)) {
            for (McShopTag mcShopTag : mcShopTagIds) {
                idList.add(mcShopTag.getId());
            }
            mcShopTagMapper.updateMcShopTag(idList);
        }

    }

}

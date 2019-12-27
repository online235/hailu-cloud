package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.McShopTagMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McShopTag;
import com.hailu.cloud.api.merchant.module.merchant.model.McShopTagModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McShopTagService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<McShopTag> addMcSHopTag(Long[] tagId, Long storeId) {
        return addOrModify(tagId, storeId, 1);
    }

    @Override
    public List<McShopTag> updateMcShopTag(Long[] tagId, Long storeId) {
        return addOrModify(tagId, storeId, 2);
    }

    @Override
    public int updateMcShopTagByStoreId(Long storeId) {
        return mcShopTagMapper.updateMcShopTagByStoreId(storeId);
    }

    @Override
    public int findMcShopTagByIdAndStoreId(Long tagId, Long storeId) {
        return mcShopTagMapper.findMcShopTagByIdAndStoreId(tagId, storeId);
    }


    public List<McShopTag> addOrModify(Long[] tagId, Long storeId, int num){
        updateMcShopTagByStoreId(storeId);
        List<McShopTag> addMcShopTag = new ArrayList<>();
        List<McShopTag> modMcShopTag = new ArrayList<>();
        Date date = new Date();

        for (Long id : tagId){
            //校验添加还是修改
            if (num != 1) {
                //校验是否存在
                if (findMcShopTagByIdAndStoreId(id, storeId) == 1) {
                    McShopTag modShopTag = new McShopTag();
                    modShopTag.setUpdateTime(date);
                    modShopTag.setStoreId(storeId);
                    modShopTag.setState(1);
                    modMcShopTag.add(modShopTag);
                    continue;
                }
            }
            McShopTag addShopTag = new McShopTag();
            addShopTag.setId(basicFeignClient.uuid().getData());
            addShopTag.setStoreId(storeId);
            addShopTag.setTagId(id);
            addShopTag.setCreateTime(date);
            addShopTag.setUpdateTime(date);
            addShopTag.setState(1);
            addMcShopTag.add(addShopTag);
        }
        if (num == 1) {
            mcShopTagMapper.addMcShopTag(addMcShopTag);
            return addMcShopTag;
        }
        mcShopTagMapper.updateMcShopTag(modMcShopTag);
        addMcShopTag.addAll(modMcShopTag);
        return addMcShopTag;
    }

}

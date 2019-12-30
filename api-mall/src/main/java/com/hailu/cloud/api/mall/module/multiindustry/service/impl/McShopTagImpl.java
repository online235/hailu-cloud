package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.hailu.cloud.api.mall.module.multiindustry.dao.McShopTagMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McShopTag;
import com.hailu.cloud.api.mall.module.multiindustry.model.McShopTagModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.McShopTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public List<McShopTagModel> findMcShopTagListByStoreId(Long storeId) {
        return mcShopTagMapper.findMcShopTagListByStoreId(storeId);
    }
}

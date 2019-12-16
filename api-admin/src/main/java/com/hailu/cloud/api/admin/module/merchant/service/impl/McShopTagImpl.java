package com.hailu.cloud.api.admin.module.merchant.service.impl;

import com.hailu.cloud.api.admin.module.merchant.dao.McShopTagMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.McShopTag;
import com.hailu.cloud.api.admin.module.merchant.service.McShopTagService;
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
    public List<McShopTag> findMcShopTagListByStoreId(Long storeId) {
        return mcShopTagMapper.findMcShopTagListByStoreId(storeId);
    }
}

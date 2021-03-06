package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.ShopGoodsClassMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodsClassSrevice {

    @Resource
    private ShopGoodsClassMapper shopGoodsClassMapper;

    /**
     * 经营类型
     * @param gcParentId
     * @return
     */
    public Object findGoodsClassList(String gcParentId){
        return shopGoodsClassMapper.findGoodsClassList(gcParentId);
    }
}

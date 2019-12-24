package com.hailu.cloud.api.merchant.module.merchant.service;


import com.hailu.cloud.api.merchant.module.merchant.dao.FeedBackMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.ShopFeedback;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class FeedBackService {


    @Autowired
    private FeedBackMapper feedBackMapper;
    @Resource
    private BasicFeignClient basicFeignClient;



    /**
     * 保存数据
     * @param shopFeedback
     * @return
     */
    public void saveFeedBack(ShopFeedback shopFeedback){

        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        shopFeedback.setUserId(merchantUserLoginInfoModel.getNumberid());
        shopFeedback.setPhone(merchantUserLoginInfoModel.getPhone());
        shopFeedback.setUserName(merchantUserLoginInfoModel.getNetworkname());
        shopFeedback.setId(basicFeignClient.uuid().getData());
        shopFeedback.setCreateTime(new Date());
        shopFeedback.setUpdateTime(new Date());
        shopFeedback.setStatus(1);
        feedBackMapper.saveFeedBack(shopFeedback);
    }


    /**
     * 更新数据
     * @param shopFeedback
     * @return
     */
    public void updateFeedBack(ShopFeedback shopFeedback){

        shopFeedback.setUpdateTime(new Date());
        feedBackMapper.updateFeedBack(shopFeedback);
    }




}

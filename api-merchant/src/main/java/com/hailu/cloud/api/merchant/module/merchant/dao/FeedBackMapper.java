package com.hailu.cloud.api.merchant.module.merchant.dao;


import com.hailu.cloud.api.merchant.module.merchant.entity.ShopFeedback;

public interface FeedBackMapper {

    /**
     * 保存数据
     * @param shopFeedback
     * @return
     */
    int saveFeedBack(ShopFeedback shopFeedback);


    /**
     * 更新数据
     * @param shopFeedback
     * @return
     */
    int updateFeedBack(ShopFeedback shopFeedback);



}

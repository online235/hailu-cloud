package com.hailu.cloud.api.merchant.module.merchant.service;

import com.hailu.cloud.api.merchant.module.merchant.entity.McCoupon;
import com.hailu.cloud.api.merchant.module.merchant.model.McCouponModel;
import com.hailu.cloud.api.merchant.module.merchant.model.McCouponOtherJsonModel;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 到店卷
 * @Date: 2019/12/20 0020
 * @program: cloud
 * @create: 2019-12-20 10:
 */
public interface McCouponService {

    /**
     *  添加到店卷
     * @param record
     */
    McCoupon insertSelective(McCouponModel record, McCouponOtherJsonModel mcCouponOtherJsonModel, String[] picturePath, Integer[] pictureType, Integer submissionType);

    /**
     * 查询到店卷详细信息
     * @param id
     * @return
     */
    McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(Long id);

    /**
     * 更改信息
     * @param record
     * @return
     */
    McCoupon updateByPrimaryKeySelective(McCoupon record, McCouponOtherJsonModel mcCouponOtherJsonModel);

    /**
     * 到店卷列表
     */
    PageInfoModel<List<McCoupon>> findMcCouponList(String volumeName, Integer toExamine, Integer shelfState, Integer page, Integer size);

    /**
     * 到店卷下架
     * @param id
     * @param shelfState
     */
    void updShelfStateById(Long id, Integer shelfState);
}

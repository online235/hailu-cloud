package com.hailu.cloud.api.admin.module.merchant.service;

import com.hailu.cloud.api.admin.module.merchant.entity.McCoupon;
import com.hailu.cloud.api.admin.module.merchant.model.McCouponModel;
import com.hailu.cloud.api.admin.module.merchant.model.McCouponOtherJsonModel;
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
     * 查询到店卷详细信息
     * @param id
     * @return
     */
    McCoupon<McCouponOtherJsonModel> selectByPrimaryKey(Long id);

    /**
     * 到店卷列表
     */
    PageInfoModel<List<McCoupon>> findMcCouponList(
            Long storeTotalType, String volumeName,
            Integer toExamine,
            Integer shelfState,
            Integer page,
            Integer size);

    /**
     * 更改审核状态
     * @param id
     * @param toExamine
     * @return
     */
    void updMcCouponById(Long id,Integer toExamine);

    /**
     * 删除到店卷
     * @mbggenerated 2019-12-20
     */
    void deleteByPrimaryKey(Long id);

}

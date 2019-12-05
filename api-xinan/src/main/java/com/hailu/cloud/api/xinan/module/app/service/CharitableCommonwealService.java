package com.hailu.cloud.api.xinan.module.app.service;

import com.hailu.cloud.api.xinan.module.app.entity.CharitableCommonweal;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
public interface CharitableCommonwealService {

    /**
     * 根据政府编号查询公益列表
     * @param adminId
     * @return
     */
    PageInfoModel<List<CharitableCommonweal>> findCharitableCommonwealByAdminId(Long adminId , Integer page, Integer size);

    /**
     * 根据编号查询详细信息
     * @param Id
     * @return
     */
    CharitableCommonweal findCharitableCommonwealById(Long Id);
}

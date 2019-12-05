package com.hailu.cloud.api.admin.module.xinan.service;

import com.hailu.cloud.api.admin.module.xinan.entity.CharitableCommonweal;
import com.hailu.cloud.api.admin.module.xinan.vo.CharitableCommonwealDto;
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
     * @param page
     * @param size
     * @return
     */
    PageInfoModel<List<CharitableCommonweal>> findCharitableCommonwealByAdminId(Integer page, Integer size);

    /**
     * 超级管理员获取公益列表
     * @return
     */
    PageInfoModel<List<CharitableCommonweal>> findCharitableCommonwealList( Integer page, Integer size);

    /**
     * 根据编号查询详细信息
     * @param Id
     * @return
     */
    CharitableCommonweal findCharitableCommonwealById(Long Id);

    /**
     * 根据编号更改信息
     * @mbggenerated 2019-12-02
     */
    CharitableCommonweal updateByPrimaryKeySelective(CharitableCommonweal record);

    /**
     * 添加公益
     * @mbggenerated 2019-12-02
     */
    CharitableCommonweal insertSelective(CharitableCommonwealDto record);
}

package com.hailu.cloud.api.admin.module.xinan.service;

import com.hailu.cloud.api.admin.module.xinan.entity.CharitableCommonweal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
public interface CharitableCommonwealService {

    /**
     * 根据政府编号查询公益列表
     * @param usersId
     * @return
     */
    List<CharitableCommonweal> findCharitableCommonwealByUsersId(@Param("usersId") Long usersId);
}

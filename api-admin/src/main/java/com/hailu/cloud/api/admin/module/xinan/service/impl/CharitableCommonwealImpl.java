package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.hailu.cloud.api.admin.module.xinan.dao.CharitableCommonwealMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.CharitableCommonweal;
import com.hailu.cloud.api.admin.module.xinan.service.CharitableCommonwealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@Service
public class CharitableCommonwealImpl implements CharitableCommonwealService {

    @Autowired
    private CharitableCommonwealMapper charitableCommonwealMapper;

    @Override
    public List<CharitableCommonweal> findCharitableCommonwealByUsersId(Long usersId) {

        return charitableCommonwealMapper.findCharitableCommonwealByUsersId(usersId);
    }
}

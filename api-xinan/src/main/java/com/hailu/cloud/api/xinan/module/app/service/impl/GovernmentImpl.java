package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.dao.GovernmentMapper;
import com.hailu.cloud.api.xinan.module.app.model.GovernmentModel;
import com.hailu.cloud.api.xinan.module.app.service.GovernmentService;
import com.hailu.cloud.api.xinan.module.app.service.INationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@Service
public class GovernmentImpl implements GovernmentService {

    @Resource
    private GovernmentMapper governmentUsersMapper;

    @Autowired
    private INationService iNationService;

    @Override
    public GovernmentModel findGovernmentUsersByCityCode(String cityCode) {
        //查询父code
        String code = iNationService.findCodeBySonCode(cityCode);
        if (StringUtils.isBlank(code)){
            code = cityCode;
        }
        return governmentUsersMapper.findGovernmentUsersByCityCode(code);
    }
}

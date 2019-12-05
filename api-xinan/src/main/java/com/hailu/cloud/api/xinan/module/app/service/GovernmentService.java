package com.hailu.cloud.api.xinan.module.app.service;

import com.hailu.cloud.api.xinan.module.app.entity.Government;
import com.hailu.cloud.api.xinan.module.app.model.GovernmentModel;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
public interface GovernmentService {

    /**
     * 根据城市查询文章
     * @param cityCode
     * @return
     */
    GovernmentModel findGovernmentUsersByCityCode(String cityCode);
}

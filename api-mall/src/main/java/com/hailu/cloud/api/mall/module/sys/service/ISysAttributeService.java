package com.hailu.cloud.api.mall.module.sys.service;

import com.hailu.cloud.api.mall.module.sys.vo.SysAttributeVO;

/**
 * 系统属性表
 * Created by somebody on 2016/6/29.
 */
public interface ISysAttributeService {
    /**
     * 根据key获取系统属性
     *
     * @param sysAttributeVO
     * @return
     * @throws Exception
     */
    SysAttributeVO getAttributeByKey(SysAttributeVO sysAttributeVO) throws Exception;

    String getDocoumentByCode(String code);

    String getToken();

    String getTokenTime();

    SysAttributeVO findAttributeValue(String attributeKey);

}

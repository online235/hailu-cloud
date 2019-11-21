package com.hailu.cloud.api.mall.module.sys.service.impl;

import com.hailu.cloud.api.mall.module.sys.dao.SysAttributeMapper;
import com.hailu.cloud.api.mall.module.sys.service.ISysAttributeService;
import com.hailu.cloud.api.mall.module.sys.vo.SysAttributeVO;
import com.hailu.cloud.api.mall.util.Const;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by somebody on 2016/6/29.
 * 系统属性表
 */
@Service
public class SysAttributeServiceImpl implements ISysAttributeService {

    @Resource
    private SysAttributeMapper sysAttributeDao;
    @Value("${redis.key.user.login.token:zyj_server_user_login_token_}")
    private String tokenKey;

    @Value("${redis.key.user.login.token-times:720}")
    private String tokenKeyTimes;

    /**
     * 根据key 获取系统属性值
     *
     * @param sysAttributeVO
     * @return
     * @throws Exception
     */
    @Override
    public SysAttributeVO getAttributeByKey(SysAttributeVO sysAttributeVO) throws Exception {
        return sysAttributeDao.getAttributeByKey(sysAttributeVO);
    }

    @Override
    public String getDocoumentByCode(String code) {
        return sysAttributeDao.getDocoumentByCode(code);
    }

    @Override
    public String getToken() {
        // TODO Auto-generated method stub
        return tokenKey;
    }

    @Override
    public String getTokenTime() {
        // TODO Auto-generated method stub
        return tokenKeyTimes;
    }

    @Override
    public SysAttributeVO findAttributeValue(String attributeKey) {
        SysAttributeVO attributeVO = sysAttributeDao.findAttributeValue(attributeKey);
        if (StringUtils.isNotEmpty(attributeVO.getAttributeValue()) && attributeVO.getAttributeValue().indexOf("http") == -1)
            attributeVO.setAttributeValue(Const.PRO_URL + attributeVO.getAttributeValue());
        return attributeVO;
    }
}

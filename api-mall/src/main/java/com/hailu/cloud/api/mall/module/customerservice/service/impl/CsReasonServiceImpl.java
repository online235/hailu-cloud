package com.hailu.cloud.api.mall.module.customerservice.service.impl;

import com.hailu.cloud.api.mall.module.customerservice.dao.CsReasonDao;
import com.hailu.cloud.api.mall.module.customerservice.service.ICsReasonService;
import com.hailu.cloud.api.mall.module.customerservice.vo.CsReasonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CsReasonServiceImpl implements ICsReasonService {

    @Autowired
    CsReasonDao csReasonDao;

    @Override
    public List<CsReasonVo> findByCsReasonType(int reasonType) {
        return csReasonDao.findByCsReasonType(reasonType);
    }

    /**
     * 返回售后原因对象
     */
    @Override
    public CsReasonVo findCsReason(int csReasonId) {
        return csReasonDao.findCsReason(csReasonId);
    }
}

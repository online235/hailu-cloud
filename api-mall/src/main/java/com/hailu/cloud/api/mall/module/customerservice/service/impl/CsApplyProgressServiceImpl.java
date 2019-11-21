package com.hailu.cloud.api.mall.module.customerservice.service.impl;

import com.hailu.cloud.api.mall.module.customerservice.dao.CsApplyProressDao;
import com.hailu.cloud.api.mall.module.customerservice.service.ICsApplyProgressService;
import com.hailu.cloud.api.mall.module.customerservice.vo.CsApplyProgressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CsApplyProgressServiceImpl implements ICsApplyProgressService {

    @Autowired
    private CsApplyProressDao csApplyProgresDao;

    @Override
    public List<CsApplyProgressVo> findByApplyProgressId(int csApplyId) {
        return csApplyProgresDao.findByApplyProgressId(csApplyId);
    }

    @Override
    public void addApplyProgress(CsApplyProgressVo csApplyProgressVo) {
        csApplyProgresDao.addApplyProgress(csApplyProgressVo);
    }


}

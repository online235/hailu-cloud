package com.hailu.cloud.api.mall.module.customerservice.service;


import com.hailu.cloud.api.mall.module.customerservice.vo.CsApplyProgressVo;

import java.util.List;

/**
 * @author Administrator
 */
public interface ICsApplyProgressService {

    /**
     * 获取 售后服务的订单列表
     *
     * @param csApplyId
     * @return
     */
    List<CsApplyProgressVo> findByApplyProgressId(int csApplyId);

    /**
     * 添加申请进程记录
     *
     * @param csApplyProgressVo
     * @return
     */
    void addApplyProgress(CsApplyProgressVo csApplyProgressVo);
}

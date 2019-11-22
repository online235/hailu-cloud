package com.hailu.cloud.api.xinan.module.app.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hailu.cloud.api.xinan.module.app.dao.SalvageOrderMapper;
import com.hailu.cloud.api.xinan.module.app.entity.SalvageOrder;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SalvageOrderService {

    @Resource
    private SalvageOrderMapper salvageOrderMapper;

    @Resource
    private BasicFeignClient basicFeignClient;

    /**
     * 保存救助支付订单
     *
     * @param salvageOrder
     * @return
     */
    public void buildOrder(SalvageOrder salvageOrder) {
        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        //捐助人编号
        salvageOrder.setMemberId(loginInfo.getUserId());
        //创建人
        salvageOrder.setCreateBy(loginInfo.getUserId());
        //生成编号
        salvageOrder.setNumberId(String.valueOf(uuidFeign.uuid()));
        //生成创建时间
        salvageOrder.setCreateDate(new Date());
        //状态
        salvageOrder.setStatus(1);
        //生成订单号
        salvageOrder.setOrderNo(IdUtil.simpleUUID());
        //订单状态
        salvageOrder.setOrderStatus(1);
        salvageOrderMapper.insertSelective(salvageOrder);
    }

    /**
     * 根据编号查询支付订单
     *
     * @param memberId
     * @param orderNo
     * @return
     */
    public SalvageOrder findSalvageOrder(String memberId, String orderNo) {
        if (StringUtils.isBlank(memberId) || StringUtils.isBlank(orderNo)) {
            return null;
        }
        return salvageOrderMapper.findSalvageOrder(memberId, orderNo);
    }

    /**
     * 根据订单号查询
     *
     * @param orderNo
     * @return
     */
    public SalvageOrder findSalvageOrderByOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return null;
        }
        return salvageOrderMapper.findSalvageOrderByOrderNO(orderNo);
    }

    /**
     * 更新订单
     *
     * @param salvageOrder
     * @return
     */
    public int updateSalvageOrder(SalvageOrder salvageOrder) {
        if (salvageOrder != null) {
            return 0;
        }
        return salvageOrderMapper.UpdateSalvageOrder(salvageOrder);
    }
}

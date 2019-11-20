package com.hailu.cloud.api.xinan.module.service;

import cn.hutool.core.util.IdUtil;
import com.hailu.cloud.api.xinan.clients.UuidFeign;
import com.hailu.cloud.api.xinan.module.dao.XaSalvageOrderMapper;
import com.hailu.cloud.api.xinan.module.entity.XaSalvageOrder;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.util.XinAnLoginAuthInfoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class XinAnXaSalvageOrderService {

    @Resource
    private XaSalvageOrderMapper xaSalvageOrderMapper;

    @Autowired
    private UuidFeign uuidFeign;

    /**
     * 保存救助支付订单
     * @param xaSalvageOrder
     * @param request
     * @return
     */
    public void buildOrder(XaSalvageOrder xaSalvageOrder, HttpServletRequest request){
        MemberLoginInfoModel loginInfo = XinAnLoginAuthInfoUtil.LoginInfo(request);
        //捐助人编号
        xaSalvageOrder.setMemberId(loginInfo.getUserId());
        //创建人
        xaSalvageOrder.setCreateBy(loginInfo.getUserId());
        //生成编号
        xaSalvageOrder.setNumberId(String.valueOf(uuidFeign.uuid()));
        //生成创建时间
        xaSalvageOrder.setCreateDate(new Date());
        //状态
        xaSalvageOrder.setStatus(1);
        //生成订单号
        xaSalvageOrder.setOrderNo(IdUtil.simpleUUID());
        //订单状态
        xaSalvageOrder.setOrderStatus(1);
        xaSalvageOrderMapper.insertSelective(xaSalvageOrder);
    }

    /**
     * 根据编号查询支付订单
     * @param memberId
     * @param orderNo
     * @return
     */
    public XaSalvageOrder findSalvageOrder(String memberId, String orderNo){
        if(StringUtils.isBlank(memberId) || StringUtils.isBlank(orderNo)){
            return null;
        }
        return xaSalvageOrderMapper.findSalvageOrder(memberId,orderNo);
    }

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    public XaSalvageOrder findSalvageOrderByOrderNo(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return null;
        }
        return xaSalvageOrderMapper.findSalvageOrderByOrderNO(orderNo);
    }

    /**
     * 更新订单
     * @param xaSalvageOrder
     * @return
     */
    public int updateSalvageOrder(XaSalvageOrder xaSalvageOrder){
        if (xaSalvageOrder != null){
            return 0;
        }
        return xaSalvageOrderMapper.UpdateSalvageOrder(xaSalvageOrder);
    }
}

package com.hailu.cloud.api.mall.module.hl.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hailu.cloud.api.mall.module.hl.dao.HlOrderMapper;
import com.hailu.cloud.api.mall.module.hl.service.IHlOrderService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.mall.hlorder.HlOrder;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 海露订单
 * @author junpei.deng
 */
@Service
public class HlOrderServiceImpl implements IHlOrderService {

    @Resource
    private HlOrderMapper hlOrderMapper;

    @Resource
    private BasicFeignClient basicFeignClient;


    @Override
    public HlOrder buildOrder(String userId, Long editId, String goodsName, String remark, String recipient,
                              String courierCompany, String courierNumber, String provinceId, String cityId, String areaId,
                              String address, Integer orderType, BigDecimal money,String invitationMember,Integer payType) {

        HlOrder order = new HlOrder();

        order.setUserId(userId);
        order.setEditId(editId);
        order.setOrderNo(IdUtil.simpleUUID());
        order.setGoodsName(goodsName);
        order.setRemark(remark);
        order.setRecipient(recipient);
        order.setCourierCompany(courierCompany);
        order.setCourierNumber(courierNumber);
        order.setProvinceId(provinceId);
        order.setCityId(cityId);
        order.setAreaId(areaId);
        order.setAddress(address);
        order.setOrderType(orderType);
        order.setMoney(money);
        order.setInvitationMember(invitationMember);
        order.setPayType(payType);
        //订单状态（1-待支付、2-待发货、3-已发货)
        order.setOrderStatus(1);
        return saveEntity(order);
    }


    @Override
    public HlOrder saveEntity(HlOrder hlOrder){
        Date dateNow = new Date();
        String userId = null;
        try {
            userId = RequestUtils.getMemberLoginInfo().getUserId();
            hlOrder.setModifyBy(userId);
        }catch (Exception e){
        }

        hlOrder.setModifyTime(dateNow);
        if(hlOrder.getId() == null){
            hlOrder.setId(basicFeignClient.uuid().getData());
            hlOrder.setCreateTime(dateNow);
            hlOrder.setCreateBy(userId);
            hlOrder.setStatus(1);
            hlOrderMapper.insert(hlOrder);
            return hlOrder;
        }
        hlOrderMapper.updateByPrimaryKeySelective(hlOrder);
        return hlOrder;
    }


    @Override
    public HlOrder findByOrderNo(String orderNo) {
        return hlOrderMapper.findByOrder(orderNo);
    }
}

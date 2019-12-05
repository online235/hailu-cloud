package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.entity.Order;
import com.hailu.cloud.api.xinan.module.app.enums.DonationOrderEnum;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author: QiuFeng:WANG
 * @Description: 捐赠订单
 * @Date: 2019/12/5 0005
 * @program: cloud
 * @create: 2019-12-05 16:
 */
@Service
public class DonationOrderService {

    @Autowired
    private OrderService orderService;

    public Order donationOrder(String rescueId, Integer itemType, String from, BigDecimal money, String invitationMember){
        String itemTypeDisPlay = "";
        DonationOrderEnum donationOrderEnum = DonationOrderEnum.of(itemType);
        switch (donationOrderEnum){
            case GOVERNMENT_CHARITY:
                itemTypeDisPlay = "政府慈善";
                break;
            case RESCUE:
                itemTypeDisPlay = "救助";
                break;
            case MUTUAL_AID:
                itemTypeDisPlay = "互助";
                break;
            default:
                break;
        }
        MemberLoginInfoModel memberLoginInfoModel = RequestUtils.getMemberLoginInfo();
        return orderService.buildDonationOrder(memberLoginInfoModel.getUserId(), rescueId, itemTypeDisPlay, from, money, invitationMember);
    }
}

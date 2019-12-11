package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.dao.DonationMapper;
import com.hailu.cloud.api.xinan.module.app.entity.Donation;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class DonationService {

    @Resource
    private DonationMapper donationMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    /**
     * 添加捐赠人
     * @param donation
     * @return
     */
    public void insOrderDonation(Donation donation){
        MemberLoginInfoModel memberLoginInfoModel = RequestUtils.getMemberLoginInfo();
        //生成uuid
        donation.setNumberId(basicFeignClient.uuid().getData());
        //缓存里拿到userId
        donation.setMemberId(memberLoginInfoModel.getUserId());
        donation.setCreatedat(new Date());
        donationMapper.insert(donation);
    }
}

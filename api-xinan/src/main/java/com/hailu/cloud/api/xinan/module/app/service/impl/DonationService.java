package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.dao.DonationMapper;
import com.hailu.cloud.api.xinan.module.app.entity.Donation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DonationService {

    @Resource
    private DonationMapper donationMapper;

    /**
     * 添加捐赠人
     * @param donation
     * @return
     */
    public void insOrderDonation(Donation donation){
        donationMapper.insertSelective(donation);
    }
}

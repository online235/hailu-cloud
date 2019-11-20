package com.hailu.cloud.api.xinan.module.service;

import com.hailu.cloud.api.xinan.module.dao.DonationMapper;
import com.hailu.cloud.api.xinan.module.entity.Donation;
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

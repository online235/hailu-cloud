package com.hailu.cloud.api.xinan.module.service;

import com.hailu.cloud.api.xinan.module.dao.XaDonationMapper;
import com.hailu.cloud.api.xinan.module.entity.XaDonation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class XinAnXaDonationService {

    @Resource
    private XaDonationMapper xaDonationMapper;

    /**
     * 添加捐赠人
     * @param xaDonation
     * @return
     */
    public void insOrderDonation(XaDonation xaDonation){
        xaDonationMapper.insertSelective(xaDonation);
    }
}

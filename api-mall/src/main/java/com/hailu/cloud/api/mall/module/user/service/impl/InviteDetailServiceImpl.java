package com.hailu.cloud.api.mall.module.user.service.impl;

import com.hailu.cloud.api.mall.module.user.dao.InviteDetailMapper;
import com.hailu.cloud.api.mall.module.user.entity.InviteDetail;
import com.hailu.cloud.api.mall.module.user.service.IInviteDetailService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.system.InvitedetailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author junpei.deng
 */
@Service
public class InviteDetailServiceImpl implements IInviteDetailService {

    @Resource
    private InviteDetailMapper inviteDetailMapper;

    @Resource
    private BasicFeignClient basicFeignClient;

    @Override
    public void add(InvitedetailModel invitedetailModel) {
        InviteDetail inviteDetail = new InviteDetail();
        BeanUtils.copyProperties(invitedetailModel,inviteDetail);
        saveEntity(inviteDetail);
    }


    /**
     * 保存或修改
     * @param inviteDetail
     * @return
     */
    private InviteDetail saveEntity(InviteDetail inviteDetail){
        if(inviteDetail.getId() == null){
            inviteDetail.setId(basicFeignClient.uuid().getData());
            inviteDetailMapper.insert(inviteDetail);
            return inviteDetail;
        }
        inviteDetailMapper.updateByPrimaryKeySelective(inviteDetail);
        return inviteDetail;
    }


}

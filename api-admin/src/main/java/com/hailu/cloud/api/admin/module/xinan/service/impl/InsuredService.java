package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.admin.module.xinan.dao.InsuredMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.Insured;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: litemall
 * @BelongsPackage: org.linlinjava.litemall.db.service.xinAn
 * @Author: junpei.deng
 * @CreateTime: 2019-10-18 09:19
 * @Description: 参保人
 */
@Slf4j
@Service
public class InsuredService {

    @Resource
    private InsuredMapper xaInsuredMapper;


    public Object findList(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Insured> insuredList = xaInsuredMapper.findListPage();
        return new PageInfo(insuredList);
    }

    /**
     * 参保人详细信息
     *
     * @param id
     * @return
     */
    public Object findInsuredById(String id) throws BusinessException {
        Insured insured = xaInsuredMapper.selectByPrimaryKey(id);
        if (insured == null) {
            throw new BusinessException("未获取任何数据数据");
        }
        return insured;
    }

    /**
     * 更改参保人状态
     *
     * @param memberStatus
     * @param id
     * @return
     */
    public void updInsureByMemberStatus(Integer memberStatus, String id) throws BusinessException {
        if (memberStatus < 1 || memberStatus > 4 || id.isEmpty()) {
            throw new BusinessException("状态或编号为空");
        }
        Insured insured = new Insured();
        insured.setMemberStatus(memberStatus);
        insured.setId(id);
        xaInsuredMapper.updateByPrimaryKeySelective(insured);
    }

}

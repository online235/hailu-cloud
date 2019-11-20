package com.hailu.cloud.api.xinan.module.service;

import com.hailu.cloud.api.xinan.clients.UuidFeign;
import com.hailu.cloud.api.xinan.module.dao.PayExpandMapper;
import com.hailu.cloud.api.xinan.module.entity.PayExpand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: litemall
 * @BelongsPackage: org.linlinjava.litemall.db.service.xinAn
 * @Author: junpei.deng
 * @CreateTime: 2019-10-18 09:49
 * @Description: 心安支付拓展表
 */
@Service
public class PayExpandService {

    @Resource
    private PayExpandMapper payExpandMapper;

    @Autowired
    private UuidFeign uuidFeign;
    /**
     * 保存或编辑
     * @param pay
     * @return
     */
    public PayExpand saveEntity(PayExpand pay){
        long dateNow = System.currentTimeMillis();
        //如果ID为空，则新增
        if(StringUtils.isBlank(pay.getId())){
            pay.setId(String.valueOf(uuidFeign.uuid()));
            payExpandMapper.insert(pay);
            return pay;
        }
        payExpandMapper.updateByPrimaryKeySelective(pay);
        return pay;
    }

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    public PayExpand findById(String id){
        if(StringUtils.isBlank(id)){
            return null;
        }
        return payExpandMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据支付ID获取支付信息
     * @param id
     * @return
     */
    public List<PayExpand> findListByPayId(String id){
        return payExpandMapper.findListByPayId(id);
    }

}

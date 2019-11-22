package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.dao.PayMapper;
import com.hailu.cloud.api.xinan.module.app.entity.Pay;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @BelongsProject: litemall
 * @BelongsPackage: org.linlinjava.litemall.db.service.xinAn
 * @Author: junpei.deng
 * @CreateTime: 2019-10-18 09:47
 * @Description: 心安支付信息
 */
@Service
public class PayService {

    @Resource
    private PayMapper payMapper;

    @Resource
    private BasicFeignClient basicFeignClient;

    /**
     * 保存或编辑
     *
     * @param pay
     * @return
     */
    public Pay saveEntity(Pay pay) {
        Date dateNow = new Date();
        //如果ID为空，则新增
        if (StringUtils.isBlank(pay.getId())) {
            pay.setId(String.valueOf(uuidFeign.uuid()));
            pay.setCreateDate(dateNow);
            pay.setCreateBy(pay.getMemberId());
            pay.setStatus(1);
            payMapper.insert(pay);
            return pay;
        }
        pay.setUpdateDate(dateNow);
        pay.setUpdateBy(pay.getMemberId());
        payMapper.updateByPrimaryKeySelective(pay);
        return pay;
    }

    /**
     * 根据ID查询信息
     *
     * @param id
     * @return
     */
    public Pay findById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return payMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据支付订单号查询信息
     *
     * @param payOrderNo
     * @return
     */
    public Pay findByPayOrderNo(String payOrderNo) {
        if (StringUtils.isBlank(payOrderNo)) {
            return null;
        }
        return payMapper.findByPayOrderNo(payOrderNo);
    }

}

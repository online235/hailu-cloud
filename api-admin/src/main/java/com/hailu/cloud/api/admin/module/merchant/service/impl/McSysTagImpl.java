package com.hailu.cloud.api.admin.module.merchant.service.impl;

import com.hailu.cloud.api.admin.module.merchant.dao.McSysTagMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McSysTagParameter;
import com.hailu.cloud.api.admin.module.merchant.service.McSysTagService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 标签Impl
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 16:
 */
@Service
public class McSysTagImpl implements McSysTagService {

    @Resource
    private McSysTagMapper mcSysTagMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public McSysTag insertSelective(McSysTagParameter record) {
        McSysTag mcSysTag = new McSysTag();
        BeanUtils.copyProperties(record, mcSysTag);
        return addOrChange(mcSysTag);
    }

    @Override
    public McSysTag selectByPrimaryKey(Long id) {
        return mcSysTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public McSysTag updateByPrimaryKeySelective(McSysTagParameter record) {
        McSysTag mcSysTag = new McSysTag();
        BeanUtils.copyProperties(record, mcSysTag);
        return addOrChange(mcSysTag);
    }

    @Override
    public void deleteByPrimaryKey(Long id, Integer deleteType) {
        if (deleteType == 1){
            McSysTag record = new McSysTag();
            record.setId(id);
            record.setState(2);
            addOrChange(record);
            return;
        }
        mcSysTagMapper.deleteByPrimaryKey(id);
    }


    public McSysTag addOrChange(McSysTag record){
        Date date = new Date();
        record.setUpdateTime(date);
        if (record.getId() == null) {
            record.setId(basicFeignClient.uuid().getData());
            record.setCreateTime(date);
            record.setState(1);
            mcSysTagMapper.insertSelective(record);
            return record;
        }
        mcSysTagMapper.updateByPrimaryKeySelective(record);
        return record;
    }

}

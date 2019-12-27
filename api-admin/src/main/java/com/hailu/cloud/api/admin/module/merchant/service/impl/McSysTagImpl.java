package com.hailu.cloud.api.admin.module.merchant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.merchant.dao.McSysTagMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McSysTagParameter;
import com.hailu.cloud.api.admin.module.merchant.service.McSysTagService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public McSysTag insertSelective(McSysTag mcSysTag) {

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
            record.setState(0);
            addOrChange(record);
            return;
        }
        mcSysTagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfoModel<List<McSysTag>> findMcSysTagList(String tagName,Integer page, Integer size) {
        Page pageData = PageHelper.startPage(page, size);
        List<McSysTag> mcSysTags = mcSysTagMapper.findMcSysTagList(tagName);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), mcSysTags);
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

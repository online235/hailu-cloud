package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.xinan.dao.CharitableCommonwealMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.CharitableCommonweal;
import com.hailu.cloud.api.admin.module.xinan.service.CharitableCommonwealService;
import com.hailu.cloud.api.admin.module.xinan.vo.CharitableCommonwealDto;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@Service
public class CharitableCommonwealImpl implements CharitableCommonwealService {

    @Resource
    private CharitableCommonwealMapper charitableCommonwealMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Override
    public PageInfoModel<List<CharitableCommonweal>> findCharitableCommonwealByAdminId( Integer page, Integer size) {
        AdminLoginInfoModel adminLoginInfoModel = RequestUtils.getAdminLoginInfo();
        Page pageData = PageHelper.startPage(page,size);
        List<CharitableCommonweal> orders ;
        if (adminLoginInfoModel.getAccountType() == 2){
            orders = charitableCommonwealMapper.findCharitableCommonwealByAdminId(adminLoginInfoModel.getId());
        }else if (adminLoginInfoModel.getAccountType() == 1){
            orders = charitableCommonwealMapper.findCharitableCommonwealList();
        }else{
            return null;
        }
        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }


    @Override
    public CharitableCommonweal findCharitableCommonwealById(Long Id) {
        return charitableCommonwealMapper.findCharitableCommonwealById(Id);
    }

    @Override
    public CharitableCommonweal updateByPrimaryKeySelective(CharitableCommonweal record) {
        record.setUpdatedat(new Date());
        return saveEntity(record);
    }

    @Override
    public CharitableCommonweal insertAndUpdate(CharitableCommonwealDto recordDto) {
        CharitableCommonweal record = new CharitableCommonweal();
        BeanUtils.copyProperties(recordDto,record);
        return saveEntity(record);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        charitableCommonwealMapper.deleteByPrimaryKey(id);
    }

    private CharitableCommonweal saveEntity(CharitableCommonweal record){
        record.setUpdatedat(new Date());
        if(record.getId() == null){
            if (record.getAdminId() == null){
                AdminLoginInfoModel adminLoginInfoModel = RequestUtils.getAdminLoginInfo();
                record.setAdminId(adminLoginInfoModel.getId());
            }
            record.setId(basicFeignClient.uuid().getData());
            record.setCratedat(new Date());
            record.setState(1);
            charitableCommonwealMapper.insertSelective(record);
            return record;
        }
        charitableCommonwealMapper.updateByPrimaryKeySelective(record);
        return record;
    }
}

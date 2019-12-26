package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.xinan.module.app.dao.CharitableCommonwealMapper;
import com.hailu.cloud.api.xinan.module.app.entity.CharitableCommonweal;
import com.hailu.cloud.api.xinan.module.app.service.CharitableCommonwealService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Value("${static.server.prefix}")
    private String serverPrefix;

    @Override
    public PageInfoModel<List<CharitableCommonweal>> findCharitableCommonwealByAdminId(Long adminId, Integer page, Integer size) {
        Page pageData = PageHelper.startPage(page,size);
        List<CharitableCommonweal> orders = charitableCommonwealMapper.findCharitableCommonwealByAdminId(adminId);
        return new PageInfoModel(pageData.getPages(), pageData.getTotal(), orders);
    }

    @Override
    public CharitableCommonweal findCharitableCommonwealById(Long Id) {
        CharitableCommonweal result = charitableCommonwealMapper.findCharitableCommonwealById(Id);
        if( result != null && StringUtils.isNotBlank(result.getArticle())){
            result.setArticle(result.getArticle().replaceAll("src=\"", "src=\"" + this.serverPrefix));
        }
        return result;
    }
}

package com.hailu.cloud.api.mall.module.message.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.message.dao.HlMessageMapper;
import com.hailu.cloud.api.mall.module.message.service.IHlMessageService;
import com.hailu.cloud.api.mall.module.message.vo.HlMessage;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息
 * @author junpei.deng
 */
@Service
public class HlMessageServiceImpl implements IHlMessageService {

    @Resource
    private HlMessageMapper hlMessageMapper;


    @Override
    public Object findListPage(String userId, Integer page, Integer size,Integer messageStatus) {
        Page pageDate = PageHelper.startPage(page,size);
        List<HlMessage> list = hlMessageMapper.findList(userId,messageStatus);
        return new PageInfoModel<>(pageDate.getPages(),pageDate.getTotal(),list);
    }


    @Override
    public void readMessage(Long id) {
        hlMessageMapper.updateMessageStatus(id,1);
    }

    @Override
    public Integer findNoReadCount(String userId) {
        return hlMessageMapper.getNoReadCount(userId);
    }
}

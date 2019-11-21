package com.hailu.cloud.api.mall.module.goods.service.impl;

import com.hailu.cloud.api.mall.module.goods.dao.IconMapper;
import com.hailu.cloud.api.mall.module.goods.service.IconService;
import com.hailu.cloud.api.mall.module.goods.vo.IconVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IconServiceImpl implements IconService {
    @Autowired
    private IconMapper iconDao;

    @Override
    public List<IconVo> iconList(int type) throws Exception {
        return iconDao.iconList(type);
    }

}

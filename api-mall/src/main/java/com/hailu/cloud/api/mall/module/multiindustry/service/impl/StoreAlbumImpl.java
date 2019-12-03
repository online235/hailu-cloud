package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import com.hailu.cloud.api.mall.module.multiindustry.dao.StoreAlbumMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.service.StoreAlbumServier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺相册
 * @Date: 2019/12/3 0003
 * @program: cloud
 * @create: 2019-12-03 15:
 */
@Service
public class StoreAlbumImpl implements StoreAlbumServier {

    @Resource
    private StoreAlbumMapper storeAlbumMapper;

    @Override
    public List<StoreAlbum> findStoreAlbumList(Long storeId) {
        return storeAlbumMapper.findStoreAlbumList(storeId);
    }
}

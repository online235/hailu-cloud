package com.hailu.cloud.api.mall.module.goods.service;


import com.hailu.cloud.api.mall.module.goods.vo.IconVo;

import java.util.List;


/**
 * @author leiqi
 * 2017/7/17
 */
public interface IconService {

    /**
     * 商城首页功能图标
     *
     * @param goodsVO
     * @return
     */
    List<IconVo> iconList(int type) throws Exception;
}

package com.hailu.cloud.api.mall.module.goods.dao;

import com.hailu.cloud.api.mall.module.goods.vo.IconVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface IconMapper {


    /**
     * 获取所有商品分类
     *
     * @param type
     * @return
     */
    List<IconVo> iconList(int type) throws Exception;

}

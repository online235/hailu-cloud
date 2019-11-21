package com.hailu.cloud.api.mall.module.capacity.dao;


import com.hailu.cloud.api.mall.module.goods.vo.HotVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface UseManualMapper {

    List<HotVo> getCapacityHotWord();

    HotVo getHotByName(String title);

    void updateHot(Integer i);

    void addHot(String title);

}

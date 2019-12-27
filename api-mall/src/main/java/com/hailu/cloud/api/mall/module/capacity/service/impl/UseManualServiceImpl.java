package com.hailu.cloud.api.mall.module.capacity.service.impl;


import com.google.common.collect.ImmutableMap;
import com.hailu.cloud.api.mall.module.capacity.dao.UseManualMapper;
import com.hailu.cloud.api.mall.module.capacity.service.IUseManualService;
import com.hailu.cloud.api.mall.module.goods.vo.HotVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class UseManualServiceImpl implements IUseManualService {

    @Resource
    private UseManualMapper useManualDao;

    @Override
    public Map<String, Object> getCapacityHotWord() {
        List<HotVo> hotVoList = useManualDao.getCapacityHotWord();
        return ImmutableMap.of("hotVoList", hotVoList);
    }
}

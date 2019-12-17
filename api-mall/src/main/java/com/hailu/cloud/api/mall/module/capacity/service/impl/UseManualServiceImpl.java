package com.hailu.cloud.api.mall.module.capacity.service.impl;


import com.hailu.cloud.api.mall.module.capacity.dao.UseManualMapper;
import com.hailu.cloud.api.mall.module.capacity.service.IUseManualService;
import com.hailu.cloud.api.mall.module.goods.vo.HotVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class UseManualServiceImpl implements IUseManualService {

    @Autowired
    private UseManualMapper useManualDao;

    @Override
    public Map<String, Object> getCapacityHotWord() {
        Map<String, Object> data = new HashMap<>();
        List<HotVo> hotVoList = useManualDao.getCapacityHotWord();
        data.put("hotVoList", hotVoList);
        return data;
    }
}

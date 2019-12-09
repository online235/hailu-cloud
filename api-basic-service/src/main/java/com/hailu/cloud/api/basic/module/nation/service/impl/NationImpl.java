package com.hailu.cloud.api.basic.module.nation.service.impl;

import com.hailu.cloud.api.basic.module.nation.dao.NationMapper;
import com.hailu.cloud.api.basic.module.nation.entity.Nation;
import com.hailu.cloud.api.basic.module.nation.service.INationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地址信息
 * @author 190726
 */
@Service
public class NationImpl implements INationService {

    @Resource
    private NationMapper nationMapper;


    @Override
    public List<Nation> findAll() {
        return nationMapper.findAll();
    }
}

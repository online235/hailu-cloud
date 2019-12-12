package com.hailu.cloud.api.basic.module.nation.service.impl;

import com.hailu.cloud.api.basic.module.nation.dao.NationMapper;
import com.hailu.cloud.api.basic.module.nation.entity.Nation;
import com.hailu.cloud.api.basic.module.nation.service.INationService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 地址信息
 * @author 190726
 */
@Service
public class NationImpl implements INationService {

    @Resource
    private NationMapper nationMapper;

    @Autowired
    private RedisStandAloneClient redisClient;

    @Override
    public List<Nation> findAll() {
        return nationMapper.findAll();
    }


    @Override
    public String findByName(String name) {
        return redisClient.hashGet(Constant.REDIS_KEY_DICT_CACHE + Constant.REDIS_KEY_DICT_CACHE_NATION_DESC,name);
    }
}

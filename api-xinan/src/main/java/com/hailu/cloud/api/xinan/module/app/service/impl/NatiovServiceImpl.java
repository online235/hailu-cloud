package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.xinan.module.app.dao.NationMapper;
import com.hailu.cloud.api.xinan.module.app.entity.Nation;
import com.hailu.cloud.api.xinan.module.app.service.INationService;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NatiovServiceImpl implements INationService {

    @Resource
    private NationMapper nationMapper;

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;

    @Override
    @Cacheable(value = "area", key = "#parentId")
    public Object findListByParentId(Long parentId) {
        List<Nation> nationList = nationMapper.findByParentId(parentId);
        JSONArray jsonArray = new JSONArray();
        for (Nation n : nationList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", n.getId());
            String name = null;
            if (StringUtils.isNotBlank(n.getProvince())) {
                name = n.getProvince();
            } else if (StringUtils.isNotBlank(n.getCity())) {
                name = n.getCity();
            } else if (StringUtils.isNotBlank(n.getDistrict())) {
                name = n.getDistrict();
            }
            jsonObject.put("name", name);
            jsonObject.put("parentId", n.getParentId());
            jsonObject.put("adCode", n.getCode());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public Nation findNationByProvince(String provinceName) {
        return nationMapper.findNationByProvince(provinceName);
    }


    @Override
    public Nation findNationByCityName(String cityName) {
        return nationMapper.findNationByCityName(cityName);
    }


    @Override
    public Nation findNationByDistrict(String district) {
        return nationMapper.findNationByDistrict(district);
    }

    @Override
    public List<Nation> findListByCodeArray(Object parameter) {
        return nationMapper.findListByCodeArray(parameter);
    }


}
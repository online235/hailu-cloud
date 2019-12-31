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
    @Cacheable(value = "area", key = "#parentCode")
    public Object findListByParentCode(String parentCode) {

        List<Nation> nationList = nationMapper.findByParentId(parentCode);
        JSONArray jsonArray = new JSONArray();
        for (Nation n : nationList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", n.getId());
            jsonObject.put("name", n.getAreaName());
            jsonObject.put("parentId", n.getParentCode());
            jsonObject.put("adCode", n.getCode());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public String findCodeBySonCode(String Code) {
        return nationMapper.findCodeBySonCode(Code);
    }

    @Override
    public String findCityNameByCode(String code) {
        return nationMapper.findCityNameByCode(code);
    }

    @Override
    public Nation findNationByAreaName(String areaName) {
        return nationMapper.findNationByAreaName(areaName);
    }



    @Override
    public List<Nation> findListByCodeArray(Object parameter) {
        return nationMapper.findListByCodeArray(parameter);
    }


}

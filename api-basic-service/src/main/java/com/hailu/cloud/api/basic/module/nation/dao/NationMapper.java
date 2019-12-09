package com.hailu.cloud.api.basic.module.nation.dao;

import com.hailu.cloud.api.basic.module.nation.entity.Nation;

import java.util.List;

public interface NationMapper {

    List<Nation> findAll();
}

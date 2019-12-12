package com.hailu.cloud.api.basic.module.nation.service;

import com.hailu.cloud.api.basic.module.nation.entity.Nation;

import java.util.List;

public interface INationService {

    List<Nation> findAll();

    String findByName(String name);
}

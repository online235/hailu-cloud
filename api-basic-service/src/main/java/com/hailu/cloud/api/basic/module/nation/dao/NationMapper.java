package com.hailu.cloud.api.basic.module.nation.dao;

import com.hailu.cloud.api.basic.module.nation.entity.Nation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NationMapper {

    List<Nation> findAll();

    List<Nation> findListByCode(@Param("code") String code);

    List<Nation> findListById(@Param("id") Long id);
}

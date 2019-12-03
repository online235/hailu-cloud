package com.hailu.cloud.api.mall.module.multiindustry.dao;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface McStoreAlbumMallMapper {


    int insertSelective(McStoreAlbum mcStoreAlbum);



    int updateByPrimaryKey(McStoreAlbum mcStoreAlbum);

    /**
     * 批量插入
     * @param parameter
     * @return
     */
    int insertStoreAlbumList(Object parameter);


    /**
     * 批量删除
     * @param parameter
     * @return
     */
    int deleteByIdList(Object parameter);


    /**
     * 获取数据列表
     * @param parameter
     * @return
     */
    List<McStoreAlbum> findListByParam(Object parameter);


    McStoreAlbum findObjectById(Long id);


    int deleteById(Long id);


}

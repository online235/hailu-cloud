package com.hailu.cloud.api.merchant.module.merchant.dao;


import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreAlbum;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface McStoreAlbumMapper {


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

    List<McStoreAlbum> findListByParam(Object parameter);


    McStoreAlbum findObjectById(Long id);


    int deleteById(Long id);


}

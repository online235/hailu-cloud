package com.hailu.cloud.api.mall.module.multiindustry.dao;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import com.hailu.cloud.api.mall.module.multiindustry.model.RotationStoreModel;
import org.apache.ibatis.annotations.Param;

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


    /**
     * 根据店铺编号类型查询店铺的相册
     * @param storeId
     * @return
     */
    List<RotationStoreModel> findStoreAlbumList(@Param("storeId") Long storeId,@Param("albumType") Integer albumType);



    McStoreAlbum findObjectById(Long id);



    int deleteById(Long id);


}

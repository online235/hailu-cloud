package com.hailu.cloud.api.mall.module.goods.dao;

import com.hailu.cloud.api.mall.module.goods.vo.CollectGoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户收藏
 *
 * @author Administrator
 */
@Mapper
public interface CollectGoodsMapper {

    /**
     * 用户收藏商品
     *
     * @param collectGoodsVo
     * @throws Exception
     */
    void addCollectGoods(CollectGoodsVo collectGoodsVo) throws Exception;


    /**
     * 删除收藏
     *
     * @param id
     * @throws Exception
     */
    void delCollectGoods(int id) throws Exception;

    /**
     * 获取用户收藏列表
     *
     * @param userId
     * @param page
     * @param rows
     * @return
     * @author 黄亮
     */
    List<CollectGoodsVo> collectGoodsList(
            @Param("userId") String userId,
            @Param("page") int page,
            @Param("rows") int rows);

    /**
     * 验证是否收藏
     */
    List<CollectGoodsVo> verificationIsCollect(
            @Param("userId") String userId,
            @Param("goodsId") int goodsId);

    /**
     * 取消收藏
     *
     * @param userId
     * @param goodsId
     * @Author huangl
     */
    void deleteCollectByGid(@Param("userId") String userId, @Param("goodsId") int goodsId);

}

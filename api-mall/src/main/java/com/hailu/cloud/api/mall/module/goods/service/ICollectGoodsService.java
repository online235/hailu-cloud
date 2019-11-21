package com.hailu.cloud.api.mall.module.goods.service;

import com.hailu.cloud.api.mall.module.goods.vo.CollectGoodsVo;

import java.util.List;

/**
 * 用户收藏
 *
 * @author leiqi
 */
public interface ICollectGoodsService {

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
     * 得到用户收藏列表
     *
     * @param userId
     * @param page
     * @param rows
     * @return
     * @author 黄亮
     */
    List<CollectGoodsVo> collectGoodsList(String userId, int page, int rows);

    /**
     * 验证该商品该用户该规格是否已经收藏
     *
     * @param userId
     * @param goodsId
     * @return
     */
    List<CollectGoodsVo> verificationIsCollect(String userId, int goodsId);

    /**
     * 删除收藏商品
     *
     * @param userId
     * @param goodsId
     * @return
     */
    Boolean deleteCollectByGid(String userId, int goodsId);
}

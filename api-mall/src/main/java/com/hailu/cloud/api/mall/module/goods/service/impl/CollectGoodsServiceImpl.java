package com.hailu.cloud.api.mall.module.goods.service.impl;

import com.hailu.cloud.api.mall.module.goods.dao.CollectGoodsMapper;
import com.hailu.cloud.api.mall.module.goods.dao.GoodsToMapper;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsListVo;
import com.hailu.cloud.api.mall.module.goods.service.ICollectGoodsService;
import com.hailu.cloud.api.mall.module.goods.vo.CollectGoodsVo;
import com.hailu.cloud.api.mall.util.Const;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收藏service
 *
 * @author Administrator
 */
@Service
public class CollectGoodsServiceImpl implements ICollectGoodsService {
    @Resource
    private CollectGoodsMapper collectGoodsDao;
    @Resource
    private GoodsToMapper goodsToDao;

    @Override
    public void addCollectGoods(CollectGoodsVo collectGoodsVo) throws Exception {
        collectGoodsDao.addCollectGoods(collectGoodsVo);
    }

    @Override
    public void delCollectGoods(int id) throws Exception {
        collectGoodsDao.delCollectGoods(id);
    }

    /**
     * 获取用户收藏列表
     */
    @Override
    public List<CollectGoodsVo> collectGoodsList(String userId, int page, int rows) {
        List<CollectGoodsVo> collectGoodsList = collectGoodsDao.collectGoodsList(userId, page, rows);
        if (collectGoodsList.size() > 0) {
            for (int i = 0; i < collectGoodsList.size(); i++) {
                CollectGoodsVo collectGoods = collectGoodsList.get(i);
                GoodsListVo goodsListVo = goodsToDao.getGoods2(collectGoods.getGoodsId());
                if (goodsListVo != null) {
                    collectGoods.setGoodsId(goodsListVo.getGoodsId());
                    collectGoods.setName(goodsListVo.getGoodsName());
                    collectGoods.setGcBigId(goodsListVo.getGcBigId());
                    collectGoods.setGcId(goodsListVo.getGcId());
                    if (StringUtils.isNotEmpty(goodsListVo.getGoodsImage()) && !goodsListVo.getGoodsImage().contains("http")) {
                        collectGoods.setSmallImg(Const.PRO_URL + goodsListVo.getGoodsImage());
                    } else {
                        collectGoods.setSmallImg(goodsListVo.getGoodsImage());
                    }
                }
            }
        }
        return collectGoodsList;
    }

    /**
     * 验证用户是否已经收藏
     */
    @Override
    public List<CollectGoodsVo> verificationIsCollect(String userId, int goodsId) {
        return collectGoodsDao.verificationIsCollect(userId, goodsId);
    }

    @Override
    public Boolean deleteCollectByGid(String userId, int goodsId) {
        collectGoodsDao.deleteCollectByGid(userId, goodsId);
        return true;
    }

}

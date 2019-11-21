package com.hailu.cloud.api.mall.module.goods.controller;


import com.hailu.cloud.api.mall.module.goods.service.ICollectGoodsService;
import com.hailu.cloud.api.mall.module.goods.vo.CollectGoodsVo;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单模块
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("api/mall")
public class CollectGoodsController {

    @Autowired
    private ICollectGoodsService collectGoodsService;

    /**
     * 添加用户收藏
     */
    @PostMapping("/addCollectGoods")
    public void addCollectGoods(
            @RequestParam int goodsId, //商品id
            @RequestParam String userId, //用户id
            @RequestParam double collectPrice, //收藏价格
            @RequestParam int goodsRuleId, //规格id
            @RequestParam String typeName //规格明细
    ) throws Exception {

        log.info("添加用户收藏|userId={}|goodsId={}|collectPrice={}|goodsRuleId={}", userId, goodsId, collectPrice, goodsRuleId);
        // 根据用户id,商品id,商品规格id得到用户是否已收藏
        List<CollectGoodsVo> collectGoodsList = collectGoodsService.verificationIsCollect(userId, goodsId);
        if (collectGoodsList.size() > 0) {
            throw new BusinessException("不能重复收藏");
        } else {
            CollectGoodsVo collectGoodsVo = new CollectGoodsVo();
            collectGoodsVo.setGoodsId(goodsId);
            collectGoodsVo.setUserId(userId);
            collectGoodsVo.setCollectPrice(collectPrice);
            collectGoodsVo.setGoodsRuleId(goodsRuleId);
            collectGoodsVo.setTypeName(typeName);
            collectGoodsVo.setCreateTime(System.currentTimeMillis());
            collectGoodsService.addCollectGoods(collectGoodsVo);
        }
    }

    /**
     * 获取收藏列表
     */
    @GetMapping("/collectGoodsList")
    public List<CollectGoodsVo> collectGoodsList(
            @RequestParam String userId,
            @RequestParam int page,
            @RequestParam int rows) throws BusinessException {

       List<CollectGoodsVo> map = new ArrayList<>();
        if (userId == null || "".equals(userId)) {
            throw new BusinessException("userid为空  ");
        }
        //用户的收藏记录
        List<CollectGoodsVo> collectGoodsList = collectGoodsService.collectGoodsList(userId, page > 1 ? (page - 1) * rows : 0, rows);
        return collectGoodsList;
    }

    /**
     * 批量删除收藏商品
     */
    @GetMapping("/delCollectGoods")
    public Map<String, Object> delCollectGoods(
            @RequestParam String idStr) throws Exception {

        for (String id : idStr.split(",")) {
            collectGoodsService.delCollectGoods(Integer.parseInt(id));
        }

        return new HashMap<>(1);
    }

    /**
     * 删除收藏根据商品id和用户id
     */
    @GetMapping("/deleteCollectByGid")
    public Boolean deleteCollectByGid(@RequestParam int goodsId, @RequestParam String userId) {
        return collectGoodsService.deleteCollectByGid(userId, goodsId);

    }

}

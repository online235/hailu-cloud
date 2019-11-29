package com.hailu.cloud.api.mall.module.goods.controller;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsListVo;
import com.hailu.cloud.api.mall.module.goods.service.IGoodsToService;
import com.hailu.cloud.api.mall.module.goods.tool.StringUtil;
import com.hailu.cloud.api.mall.module.goods.vo.RecommendVo;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 商城首页分类
 *
 * @author Administrator
 */
@Api(tags = "商城-首页分类")
@Slf4j
@RestController
@RequestMapping("api/mall")
public class CategoryController {
    @Autowired
    private IGoodsToService goodsToService;

    /**
     * 商品搜索
     */
    @PostMapping("/findByGcIdQueryGoods")
    public List<GoodsListVo> findByGcIdQueryGoods(
            @RequestParam("page") Integer page,
            @RequestParam("row") Integer row,
            //1：综合（默认）2：最新 3：人气  （为你推荐，猜你喜欢 列表只传此一个参数）4：价格
            @RequestParam("conditions") Integer conditions,
            @RequestParam(value = "goodsName", required = false) String goodsName,
            @RequestParam(value = "oby", required = false) String oby,
            @RequestParam(value = "gcId", required = false) Integer gcId,
            @RequestParam(value = "isBigClass", required = false) Integer isBigClass) throws Exception {

        if (!StringUtil.isNotEmpty(isBigClass)) {
            isBigClass = 0;
        }
        return goodsToService.verifyByGcIdQueryGoods(page, row, gcId, conditions, goodsName, oby, isBigClass);
    }

    /**
     * 得到首页下的八大类型
     */
    @PostMapping("/getHomeList")
    public List<RecommendVo> getHomeList(
            HttpServletRequest request,
            @RequestParam(value = "sessionID", required = false) String sessionId) {
        Object modelMerchant =  request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        Integer merchantType = null;
        if( modelMerchant != null){
            AuthInfo<MemberLoginInfoModel> authInfo = (AuthInfo<MemberLoginInfoModel>) modelMerchant;
            merchantType = authInfo.getUserInfo().getMerchantType();
        }
        return goodsToService.verifyHomeList(sessionId, merchantType);

    }

    /**
     * 得到分類推薦
     */
    @PostMapping("/findClassifyRecommend")
    public List<Map<String, Object>> findClassifyRecommend() throws Exception {
        return goodsToService.findClassifyRecommend();
    }

    /**
     * 得到商品分类
     */
    @PostMapping("/getListGoodsClass")
    public Map<String, Object> getListGoodsClass() {
        return goodsToService.getListGoodsClass();
    }

    /**
     * 得到分类/分类商品推荐
     */
    @GetMapping("/getGoodsClass")
    public Map<String, Object> getGoodsClass() {
        return goodsToService.getGoodsClass();
    }


    /**
     * 获取商品分类
     **/
    @GetMapping("/getParentClassList")
    public List<GoodsListVo> getParentClassList(
            @RequestParam(defaultValue = "0", required = false) int parentId) {
        log.info("获取商品分类");
        return goodsToService.getParentClassList(parentId);
    }

    /**
     * 新品首发和众筹预定
     */
    @PostMapping("/getReserveAndNew")
    public Map<String, Object> getReserveAndNew() {
        return goodsToService.getReserveAndNew();
    }

}

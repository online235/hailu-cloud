package com.hailu.cloud.api.merchant.feigns;

import com.hailu.cloud.api.merchant.module.merchant.model.Area;
import com.hailu.cloud.api.merchant.module.merchant.model.Goods;
import com.hailu.cloud.api.merchant.module.merchant.model.GoodsData;
import com.hailu.cloud.api.merchant.module.merchant.model.GoodsDetailData;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品分类查询客户端
 *
 * @Author xuzhijie
 * @Date 2019/10/21 14:47
 */
@FeignClient(name = "goods", url = "${remote.server.address}")
public interface GoodsClient {

    /**
     * 查询商品列表
     *
     * @param brandId
     * @param goodsName
     * @param goodsSerial
     * @param gcId
     * @param pageNo
     * @return
     */
    @PostMapping("/goods/list")
    ApiResponse<GoodsData> goods(
            @RequestParam(value = "searchType") Integer searchType,
            @RequestParam(value = "goodsName") String goodsName,
            @RequestParam(value = "goodsSerial") String goodsSerial,
            @RequestParam(value = "gcId") Integer gcId,
            @RequestParam(value = "gcBigId") Integer gcBigId,
            @RequestParam(value = "storeId") Long storeId,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "brandId") Integer brandId);

    @PostMapping("/goods/detail")
    ApiResponse<GoodsDetailData> detail(
            @RequestParam(value = "storeId") Long storeId,
            @RequestParam(value = "goodsId") String goodsId,
            @RequestParam(value = "gcId") String gcId);

    @PostMapping("/goods/initPublishPageData")
    ApiResponse<GoodsDetailData> initPublishPageData(
            @RequestParam(value = "storeId") Long storeId,
            @RequestParam(value = "gcId") String gcId);

    @PostMapping("/goods/saveGoods")
    ApiResponse<String> saveGoods(
            @RequestBody Goods goods,
            @RequestParam(value = "saveType") Integer saveType,
            @RequestParam(value = "jpackge") String jpackge,
            @RequestParam(value = "jparam") String jparam,
            @RequestParam(value = "prepareUpTime") String prepareUpTime,
            @RequestParam(value = "goodsSpecJson") String goodsSpecJson);

    @GetMapping("/goods/getChildArea")
    ApiResponse<List<Area>> getChildArea(@RequestParam(value = "id") Integer parentid);

    @GetMapping("/goods/upGoods")
    ApiResponse<String> upGoods(@RequestParam(value = "id") Integer id);

    @GetMapping("/goods/downGoods")
    ApiResponse<String> downGoods(@RequestParam(value = "id") Integer id);

    @GetMapping("/goods/deleteGoods")
    ApiResponse<String> deleteGoods(@RequestParam(value = "id") Integer id);

}

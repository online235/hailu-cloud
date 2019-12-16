package com.hailu.cloud.api.merchant.feigns;

import com.hailu.cloud.api.merchant.module.merchant.model.GoodsClass;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 商品分类查询客户端
 *
 * @Author xuzhijie
 * @Date 2019/10/21 14:47
 */
@FeignClient(name = "goodsCategory", url = "${remote.server.address}")
public interface GoodsCategoryClient {

    /**
     * 查询商品分类列表
     *
     * @return
     */
    @GetMapping("/goods/categorys")
    ApiResponse<List<GoodsClass>> categorys();

}

package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.feigns.GoodsCategoryClient;
import com.hailu.cloud.api.merchant.module.merchant.model.bak.GoodsClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author xuzhijie
 * @Date 2019/10/21 16:13
 */
@Api(tags = "商户-商品分类")
@RestController
@RequestMapping("/merchant/goods")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryClient categoryClient;

    @ApiOperation(value = "查询商品分类", notes = "<pre>" +
            "{\n" +
            "   'code':'0/-1',\n" +
            "   'msg':'成功/错误',\n" +
            "   'data':[{\n" +
            "       'gcId':'xxxx',\n" +
            "       'gcName':'xxx',         // 一级分类名称\n" +
            "       'classList':[{\n" +
            "           'gcId':'xxxx',\n" +
            "           'gcName':'xxxx',    // 二级分类名称\n" +
            "       }],\n" +
            "   }]\n" +
            "}\n" +
            "</pre>")
    @GetMapping("/categorys")
    public List<GoodsClass> categorys() {
        return categoryClient.categorys().getData();
    }

}

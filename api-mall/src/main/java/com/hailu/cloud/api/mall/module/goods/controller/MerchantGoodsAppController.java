package com.hailu.cloud.api.mall.module.goods.controller;


import com.hailu.cloud.api.mall.module.goods.service.IGoodsService;
import com.hailu.cloud.api.mall.module.goods.vo.GoodsAndGoodsSpecVo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/app/merchant/goods")
@Validated
@Api(tags = "商城-代理商-推广商品列表")
@Slf4j
public class MerchantGoodsAppController {

    @Autowired
    private IGoodsService iGoodsService;


    @ApiOperation(value = "商品信息列表", notes = "<pre>" +
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': {\n" +
            "    'total': 4,                            //总记录数\n" +
            "    'list': [\n" +
            "      {\n" +
            "        'goodsId': 387,                    //商品编号\n" +
            "        'goodsImage': '[&quot;//2019/11/4//BhXhws1572851718166df3f5766de215dbfc988428ca9a37fa.jpg&quot;',  //商品默认图像\n" +
            "        'goodsName': '超持久保温杯',         //商品名称\n" +
            "        'specGoodsPurchasePrice': 5,       //进货价格\n" +
            "        'commission': 10                   //提成金额\n" +
            "      }\n" +
            "    ],\n" +
            "    'pageNum': 1,                      //当前页 （外界传入）\n" +
            "    'pageSize': 1,                     //页数据条数（外界传入）\n" +
            "    'size': 1,                         //当前页数据条数\n" +
            "    'startRow': 1,                     //当前页面第一个元素在数据库中的行号\n" +
            "    'endRow': 1,                       //当前页面最后一个元素在数据库中的行号\n" +
            "    'pages': 4,                        //总页数\n" +
            "    'prePage': 0,                      //第一页\n" +
            "    'nextPage': 2,                     //前一页\n" +
            "    'isFirstPage': true,               //是否为第一页\n" +
            "    'isLastPage': false,               //是否为最后一页\n" +
            "    'hasPreviousPage': false,          //是否有前一页\n" +
            "    'hasNextPage': true,               //是否有下一页\n" +
            "    'navigatePages': 8,                //导航页码数\n" +
            "    'navigatepageNums': [              //所有导航页号\n" +
            "      1,\n" +
            "      2,\n" +
            "      3,\n" +
            "      4\n" +
            "    ],\n" +
            "    'navigateFirstPage': 1,\n" +
            "    'navigateLastPage': 4,\n" +
            "    'lastPage': 4,\n" +
            "    'firstPage': 1\n" +
            "  },\n" +
            "  'serverTime': 1572921370799\n" +
            "}")
    @PostMapping("listGoods")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示数量", defaultValue = "20", required = true, paramType = "query", dataType = "int")
    })
    public PageInfoModel<List<GoodsAndGoodsSpecVo>> findGoodsList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {

        MemberLoginInfoModel model = RequestUtils.getMemberLoginInfo();
        return iGoodsService.findGoodsList(model.getUserId(), page, size);
    }

}

package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.feigns.GoodsClient;
import com.hailu.cloud.api.merchant.module.merchant.model.bak.Area;
import com.hailu.cloud.api.merchant.module.merchant.model.bak.Goods;
import com.hailu.cloud.api.merchant.module.merchant.model.bak.GoodsData;
import com.hailu.cloud.api.merchant.module.merchant.model.bak.GoodsDetailData;
import com.hailu.cloud.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author xuzhijie
 * @Date 2019/10/21 16:13
 */
@Api(tags = "商户-商品管理")
@RestController
@RequestMapping("/merchant/goods")
public class GoodsController {

    @Autowired
    private GoodsClient goodsClient;


    @ApiOperation(value = "查询商品列表", notes = "<pre>" +
            "{\n" +
            "   'code':'0/-1',\n" +
            "   'msg':'成功/错误',\n" +
            "   'serverTime':1571640858311,\n" +
            "   'data':{\n" +
            "       'total': 15,                    // 总记录数\n" +
            "       'pageSize': 10,                 // 每页显示数量\n" +
            "       'rows': [{\n" +
            "           'goodsId':'xxxx',\n" +
            "           'goodsSerial':'xxxx'        // 商品货号,\n" +
            "           'goodsImage':'xxxx'         // 商品默认封面图片,\n" +
            "           'goodsName':'张三',         // 商品名称\n" +
            "           'storeName':'xxxx'          // 店铺名称,\n" +
            "           'gcId':'xxxx'               // 商品分类id,\n" +
            "           'gcName':'xxxx'             // 商品分类名称,\n" +
            "           'brandId':'xxxx'            // 商品品牌id,\n" +
            "           'brandName':'xxxx'          // 商品品牌名称,\n" +
            "           'goodsStorePrice':'xxxx'    // 商品店铺价格,\n" +
            "           'goodsState':'xxxx'         // 商品状态,30:审核通过,40:违规下架,50:审核未通过,60:待审核,\n" +
            "           'goodsShow':'xxxx'          // 商品上架1下架0仓库中2\n" +
            "       },\n" +
            "       'brandList':[{\n" +
            "           'brandId':'xxx',            // 品牌ID\n" +
            "           'brandName':'xxx',          // 品牌名称\n" +
            "       },\n" +
            "       'classList':[{\n" +
            "           'gcId':'xxx',               // 品牌ID\n" +
            "           'gcName':'xxx',             // 品牌名称\n" +
            "           'classList':[{\n" +
            "               'gcId':'xxx',           // 二级品牌ID\n" +
            "               'gcName':'xxx',         // 二级品牌名称\n" +
            "           }], // 品牌名称\n" +
            "       },\n" +
            "   ]}\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchType", value = "查询类型: 0出售中的商品、1仓库中的商品、2下架的商品", defaultValue = "0", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "goodsSerial", value = "商品货号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gcId", value = "商品分类ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gcBigId", value = "一级分类ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "brandId", value = "品牌ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "商户/商店ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "String", paramType = "query"),
    })
    @PostMapping("/")
    public GoodsData goods(
            @RequestParam(value = "searchType") Integer searchType,
            @RequestParam(value = "goodsName", required = false) String goodsName,
            @RequestParam(value = "goodsSerial", required = false) String goodsSerial,
            @RequestParam(value = "gcId", required = false) Integer gcId,
            @RequestParam(value = "gcBigId", required = false) Integer gcBigId,
            @RequestParam(value = "brandId", required = false) Integer brandId,
            @RequestParam(value = "storeId", required = false) Long storeId,
            @RequestParam(value = "pageNo", required = false) Integer pageNo) {

        ApiResponse<GoodsData> response = goodsClient.goods(searchType, goodsName, goodsSerial, gcId, gcBigId, storeId, pageNo, brandId);
        if (response.getCode() == 0) {
            return response.getData();
        }
        return null;
    }

    @ApiOperation(value = "查询商品详细信息", notes = "<pre>" +
            "{\n" +
            "   'code':'0/-1',\n" +
            "   'message':'成功/错误',\n" +
            "   'data':{\n" +
            "       'goods': {\n" +
            "           'goodsId':'',               // 商品ID\n" +
            "           'goodsName':''              // 商品名称,\n" +
            "           'goodsSubtitle':'',         // 商品副标题\n" +
            "           ...\n" +
            "       },\n" +
            "       'goodsSpecs': [{                // 商品规格\n" +
            "           'goodsSpecId':'',           // 商品规格索引id\n" +
            "           'goodsId':'',               // 商品ID\n" +
            "           'specName':''               // 规格名称,\n" +
            "           'specGoodsPrice':'',        // 规格商品价格\n" +
            "           ...\n" +
            "       }],\n" +
            "       'goodsStoreNum':'',             // 商品库存数量\n" +
            "       'imageList':[                   // 商品图片\n" +
            "           '图片1地址',\n" +
            "           '图片2地址',\n" +
            "           '图片3地址',\n" +
            "           ...\n" +
            "        ],\n" +
            "       'goodsBodyList':[               // app详情\n" +
            "           'app详情1',\n" +
            "           'app详情2',\n" +
            "           'app详情3',\n" +
            "           ...\n" +
            "        ],\n" +
            "       'attrVoList': [{                // 商品属性\n" +
            "           'attrId':'xxxx',            // 属性ID\n" +
            "           'attrName':'xxxx',          // 属性名称,\n" +
            "       }],\n" +
            "       'specMap': {                    // 商品属性MAP\n" +
            "           'key1':[{\n" +
            "               'spId':'',              // 规格id\n" +
            "               'spName':'',            // 规格名称\n" +
            "               'spValueId':'',         // 规格值id\n" +
            "               'spValueName':'',       // 规格值名称\n" +
            "               'colImg':'',            // 对应的图片\n" +
            "               'specIsOpen':'',        // 商品规格是否开启\n" +
            "           }],\n" +
            "       },\n" +
            "       'colImgMap': {                  // 规格颜色的图片\n" +
            "           'img1':'',\n" +
            "           'img2':'',\n" +
            "       },\n" +
            "       'brands':[{                     // 商品类型-品牌\n" +
            "           'brandId':'xxx',            // 品牌ID\n" +
            "           'brandName':'xxx',          // 品牌名称\n" +
            "       }],\n" +
            "       'specs':[{                      // 商品类型-规格\n" +
            "           'spId':'xxx',               // 规则ID\n" +
            "           'spName':'xxx',             // 规格名称\n" +
            "           'specValueList':[{\n" +
            "               'spValueId':'',         // 规格id\n" +
            "               'spValueName':'',       // 规格名称\n" +
            "           }]\n" +
            "       }],\n" +
            "       'goodsAttributes':[{            // 商品类型-属性\n" +
            "           'attrId':'xxxx',            // 属性ID\n" +
            "           'attrName':'xxxx',          // 属性名称,\n" +
            "           ...\n" +
            "       }],\n" +
            "       'parameterVoList':[{            // 商品参数列表\n" +
            "           'id':'xxxx',                // 参数ID\n" +
            "           'goodsId':'xxxx',           // 商品ID,\n" +
            "           'parameterName':'xxxx',     // 参数名称,\n" +
            "           'parameterValue':'xxxx',    // 参数值\n" +
            "       }],\n" +
            "       'packagingVoList':[{            // 商品包装今后列表\n" +
            "           'id':'xxxx',                // 参数ID\n" +
            "           'goodsId':'xxxx',           // 商品ID,\n" +
            "           'name':'xxxx',              // 名称,\n" +
            "           'context':'xxxx',           // 内容\n" +
            "       }],\n" +
            "       'areas':[{                      // 一级地区, 二级地区需要通过 /goods/getChildArea接口获取下级区域\n" +
            "           'areaId':'xxxx',            // 主键ID\n" +
            "           'areaName':'xxxx',          // 地区名称,\n" +
            "       }],\n" +
            "       'transport':{                   // 运费模板\n" +
            "           'id':'xxxx',                // 运费模板ID\n" +
            "           'title':'xxxx',             // 运费模板名称,\n" +
            "           'sendTplId':'xxxx',         // 发货地区模板ID,\n" +
            "           'storeId':'xxxx',           // 店铺ID,\n" +
            "           'isDel':'xxxx',             // 是否删除0:未删除;1:已删除,\n" +
            "           'isDefault':'xxxx',         // 是否是默认的运费模板 0:否 1:是,\n" +
            "           'transportExtendList':[{\n" +
            "               'type':'xxxx',          // 平邮py 快递kd EMS es\n" +
            "               'areaId':'xxxx',        // 市级地区ID组成的串，以，隔开，两端也有,\n" +
            "               ...\n" +
            "           }],\n" +
            "       },\n" +
            "       'imgServer':'',                 // 图片server路径\n" +
            "       'imgSrc':'',                    // 图片目录\n" +
            "       'gcId':'',                      // 商品分类ID\n" +
            "       'gcName':'',                    // 商品分类名称\n" +
            "       'typeId':'',                    // 商品类型ID\n" +
            "       'pcParentText':'',              // 获取售后pc默认模板\n" +
            "   }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "goodsId", value = "商品ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gcId", value = "商品分类ID", dataType = "String", paramType = "query")
    })
    @PostMapping("/detail")
    public GoodsDetailData detail(
            @RequestParam(value = "storeId", required = false) Long storeId,
            @RequestParam(value = "goodsId") String goodsId,
            @RequestParam(value = "gcId", required = false) String gcId) {

        ApiResponse<GoodsDetailData> response = goodsClient.detail(storeId, goodsId, gcId);
        if (response.getCode() == 0) {
            return response.getData();
        }
        return null;
    }

    @ApiOperation(value = "查找发布产品页所需要的初始数据", notes = "返回结果跟detail差不太多就不再写了")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "gcId", value = "商品分类ID", dataType = "String", paramType = "query")
    })
    @PostMapping("/initPublishPageData")
    public GoodsDetailData initPublishPageData(
            @RequestParam(value = "storeId", required = false) Long storeId,
            @RequestParam(value = "gcId") String gcId) {

        ApiResponse<GoodsDetailData> response = goodsClient.initPublishPageData(storeId, gcId);
        if (response.getCode() == 0) {
            return response.getData();
        }
        return null;
    }

    @ApiOperation(value = "保存商品数据", notes = "因为参数实在太多了，到时候具体联调")
    @PostMapping("/saveGoods")
    public void saveGoods(
            Goods goods,
            Integer saveType,
            String jpackge,
            String jparams,
            String prepareUpTime,
            String goodsSpecJson) {

        goodsClient.saveGoods(goods, saveType, jpackge, jparams, prepareUpTime, goodsSpecJson);
    }

    @ApiOperation(value = "查询子地区")
    @ApiImplicitParam(name = "id", value = "地区ID", dataType = "String", paramType = "query")
    @GetMapping("/getChildArea")
    public List<Area> getChildArea(@RequestParam(value = "id") Integer parentid) {
        ApiResponse<List<Area>> areas = goodsClient.getChildArea(parentid);
        return areas.getData();
    }


    /**
     * 下架商品
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "下架商品")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "String", required = true, paramType = "query")
    @RequestMapping(value = "/downGoods", method = RequestMethod.GET)
    public void downGoods(@RequestParam(value = "id") @NotNull(message = "商品ID不能为空") Integer id) {
        goodsClient.downGoods(id);
    }

    /**
     * 上架商品
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "上架商品")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "String", required = true, paramType = "query")
    @RequestMapping(value = "/upGoods", method = RequestMethod.GET)
    public void upGoods(@RequestParam(value = "id") @NotNull(message = "商品ID不能为空") Integer id) {
        goodsClient.upGoods(id);
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "String", required = true, paramType = "query")
    @RequestMapping(value = "/deleteGoods", method = RequestMethod.GET)
    public void deleteGoods(@RequestParam(value = "id") @NotNull(message = "商品ID不能为空") Integer id) {
        goodsClient.deleteGoods(id);
    }
}

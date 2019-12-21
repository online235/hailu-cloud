package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.google.common.collect.ImmutableMap;
import com.hailu.cloud.api.merchant.module.merchant.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.api.merchant.module.merchant.parameter.ShopInformationEntryParameter;
import com.hailu.cloud.api.merchant.module.merchant.result.RegisterShopInformationResult;
import com.hailu.cloud.api.merchant.module.merchant.result.ShopExamineResult;
import com.hailu.cloud.api.merchant.module.merchant.service.McManagementTypeService;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreAlbum;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.parameter.McStoreInformationModel;
import com.hailu.cloud.api.merchant.module.merchant.result.McStoreInformationResult;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreAlbumService;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McInfoService;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McStoreInformationService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@RequestMapping("/app/store")
@Validated
@Api(tags = "商户-物联云商-APP")
@Slf4j
public class McStoreInformationController {

    @Autowired
    private McStoreInformationService mcStoreInformationService;

    @Resource
    private McManagementTypeService mcManagementTypeService;

    @Resource
    private McStoreAlbumService mcStoreAlbumService;

    @Autowired
    private BasicFeignClient uuidFeignClient;

    @Resource
    private McInfoService mcInfoService;


    @ApiOperation(value = "获取当前店铺已填资料")
    @PostMapping("/getRegisterShopInformation")
    public RegisterShopInformationResult getRegisterShopInformation(){

        return mcInfoService.getRegisterShopInformationResult();
    }


    @ApiOperation(value = "提交补充店铺资料")
    @PostMapping("/submitShopInformation")
    public void submitShopInformation(@RequestBody ShopInformationEntryParameter shopInformationEntryParameter, BindingResult result) throws BusinessException {

        if (result.hasErrors()) {
            throw new BusinessException("必填信息不能为空！");
        }
        mcInfoService.submitShopInformation(shopInformationEntryParameter);

    }


    @ApiOperation(value = "查看商户入驻状态")
    @PostMapping("/getShopToExamine")
    public ShopExamineResult getShopToExamine(){

        McStoreInformation  mcStoreInformation = mcStoreInformationService.findMcStoreInformation();
        ShopExamineResult shopExamineResult = new ShopExamineResult();
        shopExamineResult.setToExamine(mcStoreInformation.getToExamine());
        return shopExamineResult;
    }



    @ApiOperation(value = "更改信息店铺信息", notes = "<prep>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功'\n" +
            "}\n" +
            "</prep>")
    @PostMapping("/changeInformation")
    @ApiImplicitParam(name = "wee", value = "每周营业日用（1星期日，2星期一）", allowMultiple = true, paramType = "query", dataType = "int")
    public void updateBYMcEntryInformation(@ModelAttribute McStoreInformationModel mcStoreInformationModel, int[] wee, BindingResult result) throws BusinessException {

        if (result.hasErrors()) {
            throw new BusinessException("必填信息不能为空！");
        }
        mcStoreInformationService.updateBYMcEntryInformation(mcStoreInformationModel, wee);
    }


    @ApiOperation(value = "更改营业状态", notes = "<prep>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功'\n" +
            "}\n" +
            "</prep>")
    @PostMapping("/updateState")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺id", allowMultiple = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "营业状态(1-营业中，2-休息中)", allowMultiple = true, paramType = "query", dataType = "int")
    })
    public void updateState(@NotNull @RequestParam(value = "id") Long id, @NotNull @RequestParam(value = "status") Integer status) throws BusinessException {

        if (id == null || status == null) {
            throw new BusinessException("参数不能为空！");
        }
        McStoreInformation mcStoreInformation = new McStoreInformation();
        mcStoreInformation.setId(id);
        mcStoreInformation.setBusinessState(status);
        mcStoreInformationService.updateByPrimaryKey(mcStoreInformation);

    }


    @ApiOperation(value = "根据店铺id查看店铺信息")
    @PostMapping("storeInformationById")
    @ApiImplicitParam(name = "id", value = "店铺id", paramType = "query", dataType = "Long", required = true)
    public McStoreInformationResult findMcStoreInformation(@RequestParam(value = "id") Long id) {

        McStoreInformationResult mcStoreInformationResult = new McStoreInformationResult();
        McStoreInformation mcStoreInformation = mcStoreInformationService.findMcStoreInformationById(id);
        BeanUtils.copyProperties(mcStoreInformation, mcStoreInformationResult);
        if (mcStoreInformation.getStoreTotalType() != null && mcStoreInformation.getStoreTotalType() != 0) {
            McManagementType mcManagementType = mcManagementTypeService.findManagementById(mcStoreInformation.getStoreTotalType());
            mcStoreInformationResult.setStoreTotalTypeDisPlay(mcManagementType.getManagementName());

        }
        if (mcStoreInformation.getStoreSonType() != null && mcStoreInformation.getStoreSonType() != 0) {
            McManagementType mcManagementType1 = mcManagementTypeService.findManagementById(mcStoreInformation.getStoreSonType());
            mcStoreInformationResult.setStoreSonTypeDisPlay(mcManagementType1.getManagementName());
        }
        return mcStoreInformationResult;

    }


    @ApiOperation(value = "商户查看店铺信息")
    @PostMapping("storeInformation")
    public McStoreInformationResult findMcStoreInformation() {

        McStoreInformationResult mcStoreInformationResult = new McStoreInformationResult();
        McStoreInformation mcStoreInformation = mcStoreInformationService.findMcStoreInformation();
        if (mcStoreInformation != null) {
            BeanUtils.copyProperties(mcStoreInformation, mcStoreInformationResult);
            if (mcStoreInformation.getStoreTotalType() != null && mcStoreInformation.getStoreTotalType() != 0) {
                McManagementType mcManagementType = mcManagementTypeService.findManagementById(mcStoreInformation.getStoreTotalType());
                mcStoreInformationResult.setStoreTotalTypeDisPlay(mcManagementType.getManagementName());

            }
            if (mcStoreInformation.getStoreSonType() != null && mcStoreInformation.getStoreSonType() != 0) {
                McManagementType mcManagementType1 = mcManagementTypeService.findManagementById(mcStoreInformation.getStoreSonType());
                mcStoreInformationResult.setStoreSonTypeDisPlay(mcManagementType1.getManagementName());
            }
        }
        return mcStoreInformationResult;
    }


    @ApiOperation(value = "更改店铺头像", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功'\n" +
            "}" +
            "<pre>")
    @PostMapping("updateStoreInformationAvatar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺id", paramType = "query", dataType = "Long", required = true),
            @ApiImplicitParam(name = "defaultHead", value = "店铺头像路径", paramType = "query", dataType = "String", required = true)
    })
    public void updateStoreInformationAvatar(@NotNull @RequestParam(value = "id") Long id, @RequestParam(value = "defaultHead") String defaultHead) {

        McStoreInformation mcStoreInformation = new McStoreInformation();
        mcStoreInformation.setId(id);
        mcStoreInformation.setDefaultHead(defaultHead);
        mcStoreInformation.setUpdateDateTime(new Date());
        mcStoreInformationService.updateByPrimaryKey(mcStoreInformation);

    }



    @ApiOperation(value = "相册列表", notes = "<prep>"
            + "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'id': 'Long',                  //相册id\n" +
            "        'storeId': 'Long',                  //店铺id\n" +
            "        'albumUrl': 'string',                  //相册路径\n" +
            "        'createTime': '2019-11-29T03:07:58.480Z',     //创建时间\n" +
            "        'updateTime': '2019-11-29T03:07:58.480Z',    //更新时间\n" +
            "    }\n" +
            "}" + "</prep>")
    @PostMapping("findStoreAlbumList")
    @ApiImplicitParam(name = "storeId", value = "店铺id", paramType = "query", dataType = "Long", required = true)
    public List<McStoreAlbum> findStoreAlbumList(@NotNull @RequestParam(value = "storeId") Long storeId) throws BusinessException {

        if (storeId == null) {
            throw new BusinessException("店铺id不能为空！");
        }
        return mcStoreAlbumService.findListByParam(ImmutableMap.of("storeId", storeId));

    }



    @ApiOperation(value = "保存店铺相册", notes = "<prep>"
            + "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "}" + "</prep>")
    @PostMapping("submitStoreAlbumUrl")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺id", paramType = "query", dataType = "Long", required = true),
            @ApiImplicitParam(name = "albumUrl", value = "相册路径", paramType = "query", dataType = "String", required = true)
    })
    public void submitStoreAlbumUrl(@NotNull @RequestParam(value = "storeId") Long storeId, @NotEmpty @RequestParam(value = "albumUrl") String albumUrl) throws BusinessException {

        if (storeId == null || albumUrl == null) {
            throw new BusinessException("参数不能为空！");
        }
        McStoreAlbum mcStoreAlbum = new McStoreAlbum();
        mcStoreAlbum.setStoreId(storeId);
        mcStoreAlbum.setAlbumUrl(albumUrl);
        mcStoreAlbumService.insertSelective(mcStoreAlbum);

    }



    @ApiOperation(value = "保存店铺相册，多张保存", notes = "<prep>"
            + "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "}" + "</prep>")
    @PostMapping("submitStoreMoreAlbumUrls")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "店铺id", paramType = "query", dataType = "Long", required = true),
            @ApiImplicitParam(name = "albumUrls", value = "相册路径(1,2,3)", allowMultiple = true, paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "albumType", value = "图片类型（环境-1、其他-2）[数组]", allowMultiple = true, paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "defaultRotation", value = "图片是否为首页轮播（是-1、否-2）[数组]", allowMultiple = true, paramType = "query", dataType = "String", required = true)
    })
    public void submitStoreMoreAlbumUrls(@NotNull @RequestParam(value = "storeId") Long storeId, String[] albumUrls, Integer[] albumType, Integer[] defaultRotation) throws BusinessException {

        if (storeId == null || albumUrls.length <= 0) {
            throw new BusinessException("参数不能为空！");
        } else if (albumUrls.length > 5) {
            throw new BusinessException("图片个数超过5张！");
        }
        List<McStoreAlbum> mcStoreAlbumList = new ArrayList<>();
        for (int i = 0; i < albumUrls.length; i++) {
            McStoreAlbum mcStoreAlbum = new McStoreAlbum();
            mcStoreAlbum.setId(uuidFeignClient.uuid().getData());
            mcStoreAlbum.setStoreId(storeId);
            mcStoreAlbum.setAlbumUrl(albumUrls[i]);
            mcStoreAlbum.setAlbumType(albumType[i]);
            mcStoreAlbum.setDefaultRotation(defaultRotation[i]);
            mcStoreAlbumList.add(mcStoreAlbum);
        }
        Map map = new HashMap();
        map.put("mcStoreAlbumList", mcStoreAlbumList);
        mcStoreAlbumService.deleteStoreAlbumByStoreId(storeId);//删除之前数据
        mcStoreAlbumService.insertStoreAlbumList(map);

    }


    @ApiOperation(value = "上传编辑信息", notes = "<prep>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功'\n" +
            "}\n" +
            "</prep>")
    @PostMapping("/submitStoreDetail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺id", paramType = "query", dataType = "Long", required = true),
            @ApiImplicitParam(name = "storeDetails", value = "店铺详情", paramType = "query", dataType = "String", required = true)
    })
    public void submitStoreDetail(@NotNull @RequestParam(value = "id") Long id, @NotEmpty @RequestParam(value = "storeDetails") String storeDetails) {

        McStoreInformation mcStoreInformation = new McStoreInformation();
        mcStoreInformation.setId(id);
        mcStoreInformation.setStoreDetails(storeDetails);
        mcStoreInformationService.updateByPrimaryKey(mcStoreInformation);
    }


    @ApiOperation(value = "获取编辑详情", notes = "<prep>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功'\n" +
            "     'data'：'String'      \n" +
            "}\n" +
            "</prep>")
    @PostMapping("/getStoreDetail")
    @ApiImplicitParam(name = "id", value = "店铺id", paramType = "query", dataType = "Long", required = true)
    public String getStoreDetail(@NotNull @RequestParam(value = "id") Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException("参数不能为空！");
        }
        McStoreInformation mcStoreInformation = mcStoreInformationService.findMcStoreInformationById(id);
        return mcStoreInformation.getStoreDetails();
    }



    @ApiOperation(value = "删除店铺相册", notes = "<prep>"
            + "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "}" + "</prep>")
    @PostMapping("deleStoreAlbumUrl")
    @ApiImplicitParam(name = "id", value = "相册id", paramType = "query", dataType = "Long", required = true)
    public void deleStoreAlbumUrl(@NotNull @RequestParam(value = "id") Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException("参数不能为空！");
        }
        mcStoreAlbumService.deleteById(id);
    }


}

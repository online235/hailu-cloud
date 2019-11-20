package com.hailu.cloud.api.merchant.module.controller;


import com.hailu.cloud.api.merchant.module.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.parameter.McQualifications;
import com.hailu.cloud.api.merchant.module.parameter.ShopInformation;
import com.hailu.cloud.api.merchant.module.service.GoodsClassSrevice;
import com.hailu.cloud.api.merchant.module.service.McEntryinFormationService;
import com.hailu.cloud.api.merchant.module.service.McManagementTypeService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.redis.enums.RedisEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhangmugui
 * @Description: 商家入驻app接口
 * @Date: 09:32 2019/11/19
 */
@RestController
@RequestMapping("/app/merchantsettledinApp")
@Validated
@Api(tags = "商户-商家入驻接口-APP")
@Slf4j
public class McEntryFormationAppController {


    @Resource
    private McEntryinFormationService mcEntryinFormationService;


    @Resource
    private McManagementTypeService mcManagementTypeService;



    @Autowired
    private GoodsClassSrevice goodsClassSrevice;

    @Autowired
    private RedisStandAloneClient redis;


    @ApiOperation(value = "本地生活圈——店铺信息保存", notes = "<pre>\n" +
            "{\n" +
            "    'code': 0,\n" +
            "    'msg': '成功',\n" +
            "    'data': {\n" +
            "        'numberId': '592028905'\n" +
            "    },\n" +
            "    'serverTime': 1574221345995\n" +
            "}"
            + "</pre>")
    @PostMapping("informationSave")
    @ResponseBody
    public Object informationSave(HttpServletRequest request, @ModelAttribute ShopInformation shopInformation, BindingResult result) throws BusinessException {

        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);

        if (result.hasErrors()) {
            throw new BusinessException("信息不能为空");
        }
        McEntryInformation mcEntryinFormation = new McEntryInformation();
        BeanUtils.copyProperties(shopInformation, mcEntryinFormation);
        mcEntryinFormation.setMcNumberId(loginInfo.getUserId());
        //查经营父类id
        McManagementType mcManagementType = mcManagementTypeService.findObjectByParentName(shopInformation.getLargeType());
        //判断二级名称在不在
        if(StringUtil.isNotEmpty(shopInformation.getSmallType())){
            mcEntryinFormation.setManagementId(mcManagementType.getManagementId());
        }
        Map map = new HashMap(2);
        //查找经营表id
        map.put("managementName", shopInformation.getSmallType());
        map.put("parentId",mcManagementType.getManagementId());
        List<McManagementType> managementTypes = mcManagementTypeService.findListByParam(map);
        mcEntryinFormation.setManagementId(managementTypes.get(0).getManagementId());
        return mcEntryinFormationService.insertSelective(mcEntryinFormation);


    }


    @ApiOperation(value = "提交资质保存", notes = "<pre>\n" +
            "{\n" +
            "    'code': 0,\n" +
            "    'msg': '成功',\n" +
            "    'data': null,\n" +
            "    'serverTime': 1572339258708\n" +
            "}\n" +
            "</pre>")
    @PostMapping("qualificationsSave")
    @ResponseBody
    public void qualificationsSave(@ModelAttribute McQualifications mcQualifications, BindingResult result) throws BusinessException {

        if (result.hasErrors()) {
            throw new BusinessException("信息不能为空");
        }

        McEntryInformation mcEntryinFormation = new McEntryInformation();
        BeanUtils.copyProperties(mcQualifications, mcEntryinFormation);
        mcEntryinFormationService.updateSelective(mcEntryinFormation);


    }




    @ApiOperation(value = "提交审核", notes = "<pre>\n" +
            "{\n" +
            "    'code': 0,\n" +
            "    'msg': '成功',\n" +
            "    'data': null,\n" +
            "    'serverTime': 1572339258708\n" +
            "}\n" +
            "</pre>")
    @PostMapping("submitAudit")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query")
    })
    public void submitAudit(HttpServletRequest request ,String code, @Pattern(regexp = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$", message = "手机号不正确") String phone, BindingResult result) throws BusinessException {

        if (result.hasErrors()) {
            throw new BusinessException("信息不能为空");
        }
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        String val = redis.stringGet(RedisEnum.DB_1.ordinal(), phone);

        if (!loginInfo.getMemberMobile().equals(phone)){
            throw new BusinessException("手机号码与注册时不匹配");
        }
        if (!code.equals(val) ) {
            throw new BusinessException("验证码不正确");
        }

    }

    @ApiOperation(value = "获取经营类型", notes = "<pre>\n" +
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': [\n" +
            "    {\n" +
            "      'gcId': 237,\n" +
            "      'gcParentId': 0,\n" +
            "      'gcName': '医疗器械'\n" +
            "    },\n" +
            "    {\n" +
            "      'gcId': 238,\n" +
            "      'gcParentId': 0,\n" +
            "      'gcName': '百货购物'\n" +
            "    }\n" +
            "  ],\n" +
            "  'serverTime': 1574234524345\n" +
            "}" +
            "</pre>")
    @PostMapping("businessType")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gcParentId", value = "类型编号", paramType = "query")
    })
    public Object findGoodsList(@NotBlank(message = "编号不能为空") String gcParentId){

            return goodsClassSrevice.findGoodsClassList(gcParentId);
    }


}

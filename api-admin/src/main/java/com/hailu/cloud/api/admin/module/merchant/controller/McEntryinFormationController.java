package com.hailu.cloud.api.admin.module.merchant.controller;

import com.hailu.cloud.api.admin.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.admin.module.merchant.service.McEntryinFormationService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商家入驻接口
 * @Date: 16:32 2019/11/2 0002
 */
@RestController
@RequestMapping("/pc/merchantsettledin")
@Validated
@Api(tags = "商户-百货入驻-管理后台")
@Slf4j
public class McEntryinFormationController {


    @Autowired
    private McEntryinFormationService mcEntryinFormationService;

    /**
     * 外部存储的绝对路径
     */
    @Value("${file.store.path:/opt/filestore}")
    private String fileStorePath;


    @ApiOperation(value = "显示商家入驻信息", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'totalPage': 1,    // 总页数\n" +
            "        'total': 4,        // 总记录数\n" +
            "        'datas': [{\n" +
            "                'numberId': '592028905',                                   // 编号\n" +
            "                'mcNumberId': '1031302017',                                // 商家编号\n" +
            "                'shopName': '好喝的奶茶',                                   // 店铺名称\n" +
            "                'phone': '1312863894',                                     // 手机号码\n" +
            "                'idCard': '123212343232454324',                            // 身份证号码\n" +
            "                'idcardImgx': '/String',                                   // 身份证正面\n" +
            "                'idcardImgy': '/String',                                   // 身份证反面\n" +
            "                'businessLicenseNumber': '53446457567573524123232324232',  // 营业执照注册号\n" +
            "                'nameOfLegalPerson': '/String',                            // 法人姓名\n" +
            "                'licensePositive': '/String',                              // 营业执照正面照\n" +
            "                'thirdPartyLinks': '/String',\n" +
            "                'toExamine': '1',                                          // 审核状态\n" +
            "                'cityCode': 37,\n" +
            "                'provinceCode': 36,\n" +
            "                'areaCode': 38,\n" +
            "                'detailAddress': '龙街巷43号',\n" +
            "                'createdat': 1574221332240,                                // 创建时间\n" +
            "                'updatedat': 1574221332240                                 // 更新时间\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}" +
            "</pre>")
    @PostMapping("entryInformationList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopname", value = "店铺名称", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", paramType = "query", dataType = "String"),
    })
    public Object selectMcEntryinFormationList(
            String shopname,
            String phone,
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
            @Range(min = 10, max = 200, message = "每页显示数量只能在10~200之间")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return mcEntryinFormationService.selectMcEntryinFormationList(shopname, phone, Integer.parseInt(pageNum), pageSize);
    }


    @ApiOperation(notes = "", value = "商家入驻信息详情")
    @PostMapping("mcEntryInFormationDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "信息编号", required = true, paramType = "query")
    })
    public McEntryInformation selectByPrimaryKey(
            @NotBlank(message = "编号不能为空") String numberId) {

        return mcEntryinFormationService.selectByPrimaryKey(numberId);
    }


    @ApiOperation(notes = "", value = "更改入驻审核状态")
    @PostMapping("changeState")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "信息编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "toExamine", value = "状态", required = true, paramType = "query")
    })
    public void updateToExamineByNumberId(
            @NotBlank(message = "编号不能为空") String numberId,
            @NotNull(message = "更改的状态不能为空") Integer toExamine) {

        mcEntryinFormationService.updateToExamineByNumberId(numberId, toExamine);
    }


    @ApiOperation(value = "更改商家入驻信息")
    @PostMapping("updEntryInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopname", value = "店铺名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "realname", value = "真实姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "idcard", value = "身份证号码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "idcardtermofvalidity", value = "身份证有效期", paramType = "query"),
            @ApiImplicitParam(name = "businessname", value = "执照名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "nameoflegalperson", value = "法人姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "licensedate", value = "执照有效日期", paramType = "query"),
            @ApiImplicitParam(name = "longtermlicense", value = "营业执照是否为长期", paramType = "query"),
            @ApiImplicitParam(name = "longtermcertificate", value = "身份证是否为长期", paramType = "query"),
            @ApiImplicitParam(name = "serviceProviderOrNot", value = "是否为服务商", required = true, paramType = "query"),
            @ApiImplicitParam(name = "remarks", value = "备注", paramType = "query"),
            @ApiImplicitParam(name = "firstManagementTypeId", value = "经营类型表一级编号", paramType = "query"),
            @ApiImplicitParam(name = "secondManagementTypeId", value = "经营类型表二级编号id", paramType = "query"),

            @ApiImplicitParam(name = "licensepositivefile", value = "营业执照正面", required = true, paramType = "query"),
            @ApiImplicitParam(name = "idcardimgxfile", value = "证件照正面", required = true, paramType = "query"),
            @ApiImplicitParam(name = "idcardimgyfile", value = "证件照反面", required = true, paramType = "query")
    })
    public void updateMcEntryInformation(McEntryInformation mcEntryinFormation, HttpServletRequest request) throws BusinessException {
        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();

        if (mcEntryinFormation == null) {
            throw new BusinessException("信息为空");
        }
        mcEntryinFormation.setMcNumberId(loginInfo.getUserId());
        mcEntryinFormationService.updateMcEntryInformation(mcEntryinFormation);
    }


    @ApiOperation(value = "删除商家入驻信息")
    @PostMapping("delEntryInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query")
    })
    public void deleteByPrimaryKey(
            @NotBlank(message = "编号不能为空") String numberId) {

        mcEntryinFormationService.deleteByPrimaryKey(numberId);
    }


}

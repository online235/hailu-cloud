package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.service.impl.InsuredService;
import com.hailu.cloud.api.xinan.module.app.service.impl.OrderService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.IDCardCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: litemall
 * @BelongsPackage: org.linlinjava.litemall.wx.web
 * @Author: junpei.deng
 * @CreateTime: 2019-10-17 11:26
 * @Description: 我要加入接口
 */
@RestController
@RequestMapping("xinan/app/join/")
@Validated
@Api(tags = "心安-我要加入")
@Slf4j
public class AppJoinController {

    @Autowired
    private InsuredService insuredService;


    @Autowired
    private OrderService orderService;

    @Autowired
    private IDCardCheck idCardCheck;


    /**
     * 保存参保人信息
     *
     * @param name
     * @param idCard
     * @param
     * @param memberRelation
     * @param type
     * @param isYearEnjoy
     * @param photoUrl
     * @return
     */
    @ResponseBody
    @PostMapping("/saveInsourd")
    @ApiOperation(notes = "<pre>{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": {\n" +
            "    \"code\": 0,\n" +
            "    \"msg\": \"成功\",\n" +
            "    \"data\": {\n" +
            "      \"money\": 0.01\n" +
            "    },\n" +
            "    \"serverTime\": 1571740176208\n" +
            "  },\n" +
            "  \"serverTime\": 1571740176208\n" +
            "}</pre>", value = "保存参保人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "idCard", value = "证件号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memberRelation", value = "参保人关系(0-本人、1-配偶、2-父母、3-子女、4-朋友)", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "参保人证件类型(1-身份证、2-护照、3-军官证、4-士兵证、5-港澳台居民往来通行证、6-临时身份证、7-户口簿、8-警官证、9-其他、10-外国人永久居留证、11-边民出入通行证)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isYearEnjoy", value = "是否享受年费机制(1-是、2-否)", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "photoUrl", value = "图片地址（享受年费的时候必须上传）", paramType = "query", dataType = "String"),
    })
    public Object saveInsourd(
            HttpServletRequest request,
            @NotBlank(message = "名称不能为空") String name,
            @NotBlank @Length(min = 18, max = 18, message = "身份证格式不正确") String idCard,
            @NotBlank(message = "参保人关系不能为空") Integer memberRelation,
            @NotBlank(message = "参保人证件类型不能为空") Integer type,
            @NotBlank(message = "是否享受年费机制值不能为空") Integer isYearEnjoy,
            String photoUrl) throws BusinessException {

        log.info("保存参保人信息:姓名" + name + " 身份证号码：" + idCard + " 身份证号码：" + idCard + " 参保人关系：" + memberRelation + " 参保人证件类型：" + type + " 是否享受年费机制：" + isYearEnjoy + " 图片地址：" + photoUrl);
        //如果身份类型为身份证，则校验身份的准确性
        if (type == 1 && !idCardCheck.checkIdCard(idCard, name)) {
            //校验身份合法性
            //TODO:身份不合法
            throw new BusinessException("身份证与姓名不匹配");
        }
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        return insuredService.addOrEditInsured(name, idCard, loginInfo.getUserId(), memberRelation, type, isYearEnjoy, photoUrl);
    }


    /**
     * 获取参保人未支付的订单
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/findNoPayList")
    @ApiOperation(notes = "{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": {\n" +
            "    \"money\": 0.02,\n" +
            "    \"list\": [\n" +
            "      {\n" +
            "        \"memberRelation\": 1,\n" +
            "        \"name\": \"Test\",\n" +
            "        \"id\": \"03534b51610a46f6a738592b69c19bf5\",\n" +
            "        \"value\": \"440********421\",\n" +
            "        \"isType\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"memberRelation\": 1,\n" +
            "        \"name\": \"邓俊培\",\n" +
            "        \"id\": \"03534b51610a46f6a738592b69c19bf6\",\n" +
            "        \"value\": \"440********310\",\n" +
            "        \"isType\": 1\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"serverTime\": 1571740676302\n" +
            "}", value = "获取参保人未支付的订单")
    public Object findNoPayList(HttpServletRequest request) {
        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        log.info("获取参保人列表");
        return orderService.findListByMemberIdAndMemberStatus(loginInfo.getUserId());
    }


    /**
     * 删除参保人
     *
     * @param
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteById")
    @ApiOperation(notes = "{\n" +
            "  \"code\": 0,\n" +
            "  \"data\": {},\n" +
            "  \"msg\": \"string\",\n" +
            "  \"serverTime\": 0\n" +
            "}", value = "删除参保人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参保人ID", required = true, paramType = "query", dataType = "String"),
    })
    public void deleteById(@NotBlank(message = "ID不能为空") String id) {
        log.info("删除参保人  id:" + id);
        orderService.deleteById(id);
    }


    /**
     * 根据参保人ID查询详细信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/findDetailById")
    @ApiOperation(notes = "{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": {\n" +
            "    \"photoUrl\": null,\n" +
            "    \"isYearEnjoy\": 1,\n" +
            "    \"memberRelation\": 1,\n" +
            "    \"name\": \"Test\",\n" +
            "    \"remark\": null,\n" +
            "    \"memberValidity\": null,\n" +
            "    \"type\": 1,\n" +
            "    \"value\": \"4408251995402421\"\n" +
            "  },\n" +
            "  \"serverTime\": 1571737095951\n" +
            "}", value = "根据参保人ID查询详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参保人ID", required = true, paramType = "query", dataType = "String"),
    })
    public Object findDetailById(@NotBlank(message = "ID不能为空") String id) {
        log.info("查询参保人明细  id:{}", id);
        return insuredService.findDetailById(id);
    }

    /**
     * 修改参保人资料
     *
     * @param name
     * @param idCard
     * @param id
     * @param memberRelation
     * @param type
     * @param isYearEnjoy
     * @param photoUrl
     * @return
     */
    @ResponseBody
    @PostMapping("/editInsurd")
    @ApiOperation(notes = "{\n" +
            "  \"code\": 0,\n" +
            "  \"data\": {},\n" +
            "  \"msg\": \"string\",\n" +
            "  \"serverTime\": 0\n" +
            "}", value = "修改参保人资料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参保人ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "idCard", value = "证件号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memberRelation", value = "参保人关系", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "参保人证件类型", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isYearEnjoy", value = "是否享受年费机制", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "photoUrl", value = "图片地址", paramType = "query", dataType = "String"),
    })
    public Object editInsurd(
            @NotBlank(message = "名称不能为空") String name,
            @Length(min = 18, max = 18, message = "身份证格式不正确") String idCard,
            @NotBlank(message = "编号不能为空") String id,
            @NotBlank(message = "参保人关系不能为空") Integer memberRelation,
            @NotBlank(message = "参保人证件类型不能为空") Integer type,
            @NotBlank(message = "是否享受年费机制值不能为空") Integer isYearEnjoy,
            String photoUrl) throws BusinessException {

        log.info("修改参保人信息:姓名" + name + " 身份证号码：" + idCard + " 身份证号码：" + idCard + " 参保人关系：" + memberRelation + " 参保人证件类型：" + type + " 是否享受年费机制：" + isYearEnjoy + " 图片地址：" + photoUrl);
        if (type == 1 && !idCardCheck.checkIdCard(idCard, name)) {
            //校验身份合法性
            throw new BusinessException("身份证与姓名不匹配");
        }
        return insuredService.editInsurd(name, idCard, id, memberRelation, type, isYearEnjoy, photoUrl);
    }

    /**
     * 查询名下的参保人信息
     *
     * @param request
     * @return
     */
    @ApiOperation(notes = "<pre>{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"insuredMemberId\": \"\",              //会员ID\n" +
            "      \"name\": \"看剧透\",                    //名称\n" +
            "      \"memberStatus\": 3,                     //会员状态（1-代付款、2-待审核、3-观察期、4-驳回 ）\n" +
            "      \"insuredId\": \"8b381a3406364f4481505fa877006a4d\", //参保人ID\n" +
            "      \"memberValidity\": \"2020-10-30 10:46:56\", //续费日期\n" +
            "      \"remainingDay\": 88,                        //观察期剩余时间（状态为观察期的时候才有）\n" +
            "      \"type\": 1,                                  //(1-身份证、2-护照、3-军官证、4-士兵证、5-港澳台居民往来通行证、6-临时身份证、7-户口簿、8-警官证、9-其他、10-外国人永久居留证、11-边民出入通行证)\n" +
            "      \"value\": \"556********865\"                   //证件值\n" +
            "    },\n" +
            "    {\n" +
            "      \"insuredMemberId\": \"\",\n" +
            "      \"name\": \"tert\",\n" +
            "      \"memberStatus\": 2,\n" +
            "      \"insuredId\": \"b09e44bb72b84dfbbd7f4d7371d1dd85\",\n" +
            "      \"memberValidity\": \"\",\n" +
            "      \"type\": 1,\n" +
            "      \"value\": \"037********994\"\n" +
            "    }\n " +
            "{\n" +
            "      \"money\": 0.01,      //金额\n" +
            "      \"insuredMemberId\": \"\",\n" +
            "      \"memberRelation\": 4, //关系（0-本人、1-配偶、2-父母、3-子女、4-朋友）\n" +
            "      \"name\": \"被套\",\n" +
            "      \"memberStatus\": 1,\n" +
            "      \"insuredId\": \"62fd12f82d83460f91e8bc24aa7f773d\",\n" +
            "      \"memberValidity\": \"\",\n" +
            "      \"type\": 1,\n" +
            "      \"value\": \"565********433\",\n" +
            "      \"isType\": 1      //1-首年、2-续费、3-免费\n" +
            "    }" +
            "  ],\n" +
            "  \"serverTime\": 1572502565783\n" +
            "}", value = "查询名下的参保人信息")
    @ResponseBody
    @PostMapping("/findInsurdList")
    public Object findInsurdList(HttpServletRequest request) {

        MemberLoginInfoModel loginInfo = (MemberLoginInfoModel) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        log.debug("查询参保人列表:{}", loginInfo.getUserId());
        return insuredService.findListByMemberId(loginInfo.getUserId());
    }

}

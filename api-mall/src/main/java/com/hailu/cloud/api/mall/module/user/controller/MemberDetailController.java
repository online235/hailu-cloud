package com.hailu.cloud.api.mall.module.user.controller;

import com.hailu.cloud.api.mall.module.user.service.IMemberDetailService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.math.BigDecimal;

/**
 * @author junpei.deng
 */
@Validated
@RestController
@RequestMapping("/memberDetail")
@Slf4j
@Api(tags = "会员详细")
public class MemberDetailController {

    @Resource
    private IMemberDetailService memberDetailService;

    /**
     * 添加服务区域
     * @param userId
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param address
     * @param wantBuyType   购买的类型（1-区代、2-城市合伙人、3-销售）
     * @return
     */
    @PostMapping("/addAddress")
    public Long addAddress(@RequestParam("userId") String userId,
                           @RequestParam("provinceId") String provinceId,
                           @RequestParam("cityId") String cityId,
                           @RequestParam("areaId") String areaId,
                           @RequestParam("address") String address,
                           @RequestParam("wantBuyType") Integer wantBuyType,
                           @RequestParam("name") String name,
                           @RequestParam("phone") String phone){
       return memberDetailService.addAddress(userId,provinceId,cityId,areaId,address,wantBuyType,name,phone);
    }

    /**
     *  添加汇总信息
     * @param userId                会员ID
     * @param totalConsumption      累计消费
     * @param inviteMemberNum       邀请会员数量
     * @param inviteMerchatNum      邀请商家数量
     * @param invitePartnersNum     邀请合伙人数量
     * @param salesPerformance      销售业绩
     */
    @PostMapping("/addTotal")
    public void addTotal(@RequestParam("userId") String userId,
                         @RequestParam("totalConsumption") BigDecimal totalConsumption,
                         @RequestParam("inviteMemberNum") Integer inviteMemberNum,
                         @RequestParam("inviteMerchatNum") Integer inviteMerchatNum,
                         @RequestParam("invitePartnersNum") Integer invitePartnersNum,
                         @RequestParam("salesPerformance") BigDecimal salesPerformance){
        memberDetailService.addTotal(userId, totalConsumption, inviteMemberNum, inviteMerchatNum, invitePartnersNum, salesPerformance);
    }


    @ApiOperation(value = "查询市公司下面的合伙人",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"userId\": \"12036800466011140\",       //用户ID\n" +
            "      \"inviteMemberNum\": 1002,               //邀请会员数\n" +
            "      \"invitePartnersNum\": 510,              //邀请合伙人数量\n" +
            "      \"salesPerformance\": 21321,             //销售业绩\n" +
            "      \"provinceId\": \"130000\",\n" +
            "      \"provinceIdStr\": \"河北省\",            //省\n" +
            "      \"cityId\": \"130100\",\n" +
            "      \"cityIdStr\": \"石家庄市\",                 //市\n" +
            "      \"areaId\": \"130102\",\n" +
            "      \"areaIdStr\": \"长安区\",                  //区县\n" +
            "      \"address\": \"奥园广场\",                   //详细地址\n" +
            "      \"name\": \"Test\",                          //名称\n" +
            "      \"phone\": \"13800138003\"                   //电话\n" +
            "    },\n" +
            "    {\n" +
            "      \"userId\": \"12163231418304000\",\n" +
            "      \"inviteMemberNum\": 50,\n" +
            "      \"invitePartnersNum\": 0,\n" +
            "      \"salesPerformance\": 0,\n" +
            "      \"provinceId\": \"110000\",\n" +
            "      \"provinceIdStr\": \"北京市\",\n" +
            "      \"cityId\": \"110000\",\n" +
            "      \"cityIdStr\": \"北京市\",\n" +
            "      \"areaId\": \"110102\",\n" +
            "      \"areaIdStr\": \"西城区\",\n" +
            "      \"address\": \"1313231\",\n" +
            "      \"name\": \"test\",\n" +
            "      \"phone\": \"13800138002\"\n" +
            "    }\n" +
            "  ]\n" +
            "}")
    @GetMapping("/findServiceProvidersList")
    public Object findServiceProvidersList(){
        log.info("查询市公司下面的合伙人");
        return memberDetailService.findServiceProvidersList();
    }

    /**
     * 分页查询会员邀请信息
     * @param value
     * @param type
     * @return
     */
    @ApiOperation(value = "分页查询会员邀请信息",notes = "类型不同返回参数也不同：" +
            "------会员返回参数-------{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": {\n" +
            "    \"totalPage\": 1,\n" +
            "    \"total\": 1,\n" +
            "    \"datas\": [\n" +
            "      {\n" +
            "        \"totalConsumption\": 20,          //累计消费\n" +
            "        \"userId\": 11653689383197696,          //会员ID\n" +
            "        \"name\": \"品如\",                  //名称\n" +
            "        \"phone\": \"131****509\",        //电话号码\n" +
            "        \"joinTime\": \"2019-12-26 15:37:27\", //加入时间\n" +
            "        \"icon\": \"/store/2019-12-05/17d075c2a97b4aba95cd76428567bc74-1575527848067.jpeg\"    头像地址\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}\n" +
            "-------城市合伙人返回参数--------{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": {\n" +
            "    \"totalPage\": 1,\n" +
            "    \"total\": 1,\n" +
            "    \"datas\": [\n" +
            "      {\n" +
            "        \"userId\": \"12163231418304000\",     //用户ID\n" +
            "        \"inviteMemberNum\": 50,               //邀请会员\n" +
            "        \"invitePartnersNum\": 10,             //邀请合伙人\n" +
            "        \"salesPerformance\": 15000,           //销售业绩\n" +
            "        \"provinceId\": \"110000\",\n" +
            "        \"provinceIdStr\": \"北京市\",            //省\n" +
            "        \"cityId\": \"110000\",\n" +
            "        \"cityIdStr\": \"北京市\",                //市\n" +
            "        \"areaId\": \"110102\",\n" +
            "        \"areaIdStr\": \"西城区\",                //区县\n" +
            "        \"address\": \"1313231\",                  //详细地址\n" +
            "        \"name\": \"test\",                        //名称\n" +
            "        \"phone\": \"138****002\",                 //电话号码\n" +
            "        \"joinTime\": \"2019-12-26 14:37:42\"      //加入时间\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}\n" +
            "---------销售返回参数---------{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": {\n" +
            "    \"totalPage\": 1,\n" +
            "    \"total\": 1,\n" +
            "    \"datas\": [\n" +
            "      {\n" +
            "        \"userId\": \"7c9dfe60ac2c40c0b194b5eb84470ec4\",      //用户ID\n" +
            "        \"inviteMerchatNum\": 50,                              //邀请商家数量\n" +
            "        \"salesPerformance\": 50,                              //销售业绩\n" +
            "        \"provinceId\": \"130000\",\n" +
            "        \"provinceIdStr\": \"河北省\",                           //省\n" +
            "        \"cityId\": \"130100\",\n" +
            "        \"cityIdStr\": \"石家庄市\",                               //市\n" +
            "        \"areaId\": \"130102\",\n" +
            "        \"areaIdStr\": \"长安区\",                                //区县\n" +
            "        \"address\": \"正方、云溪谷\",                               //详细地址\n" +
            "        \"name\": \"销售\",                                      //名称\n" +
            "        \"phone\": \"138****003\",                                 //电话号码\n" +
            "        \"joinTime\": \"2019-12-26 15:41:28\"                      //加入时间\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query",dataType = "int"),
        @ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "query"),
        @ApiImplicitParam(name = "type", value = "类型（0-会员，2-城市合伙人、3-销售人员、4-商家）", required = true, paramType = "query"),
        @ApiImplicitParam(name = "userId", value = "会员UserId", required = true, paramType = "query"),
        @ApiImplicitParam(name = "value", value = "查询内容", required = false, paramType = "query"),
        @ApiImplicitParam(name = "memberAll", value = "会员是否查询全部(1-是)（针对市公司查询）", required = false, paramType = "query"),
    })
    @GetMapping("/findMemberDetail")
    public Object findMemberDetail(
            @RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
            @RequestParam(value = "size",defaultValue = "20",required = false) @Max(value = 200, message = "每页最多显示200条数据")Integer size,
            @RequestParam(value = "value",required = false)String value,
            @RequestParam("type")Integer type,
            @RequestParam("userId")String userId,
            @RequestParam(value = "memberAll",required = false)Integer memberAll
    ) throws BusinessException {
        log.info("分页查询会员邀请信息");
        return memberDetailService.findMemberDetail(page,size,value,type,userId,memberAll);
    }


}

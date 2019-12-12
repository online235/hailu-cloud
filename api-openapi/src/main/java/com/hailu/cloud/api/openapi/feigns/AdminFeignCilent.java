package com.hailu.cloud.api.openapi.feigns;

import com.hailu.cloud.api.openapi.model.entity.CharitableCommonweal;
import com.hailu.cloud.api.openapi.model.entity.Government;
import com.hailu.cloud.api.openapi.model.vo.CharitableCommonwealDto;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.response.ApiResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 后台政府公益对外接口
 * @Date: 2019/12/11 0011
 * @program: cloud
 * @create: 2019-12-11 10:
 */
@FeignClient(name = "service-api-admin")
public interface AdminFeignCilent {

    /**
     * 添加文章
     * @param commonwealArticle
     * @return
     * @throws BusinessException
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_ADMIN + "/app/xinan/addGovernment")
    ApiResponse<Government> insertSelective(
            @RequestParam("commonwealArticle") String commonwealArticle);

    /**
     * 修改文章
     * @param commonwealArticle
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_ADMIN + "/app/xinan/modify")
    ApiResponse<Government> modifyCommonwealArticle(
            @RequestParam("commonwealArticle") String commonwealArticle);

    /**
     * 查询账号下详细信息
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_ADMIN + "/app/xinan/detailedInformation")
    ApiResponse<Government> findCommonwealArticle();

    /**
     * 用户根据token查询公益列表
     * @param page
     * @param size
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_ADMIN + "/app/xinAn/governmentPublicWelfare")
    ApiResponse<PageInfoModel<List<CharitableCommonweal>>> findCommonwealArticle(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size);

    /**
     * 根据编号查询公益详细
     * @param Id
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_ADMIN + "/app/xinAn/detailed")
    ApiResponse<CharitableCommonweal> findCharitableCommonwealById(
            @RequestParam("Id") Long Id);


    /**
     * 添加公益或者根据编号更改信息
     * @param record
     * @return
     */
    @PostMapping("/addPublicInterest")
    ApiResponse<CharitableCommonweal> insertAndUpdate(
            @RequestBody CharitableCommonwealDto record);

}

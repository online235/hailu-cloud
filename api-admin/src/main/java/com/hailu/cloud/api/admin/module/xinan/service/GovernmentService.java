package com.hailu.cloud.api.admin.module.xinan.service;

import com.hailu.cloud.api.admin.module.xinan.entity.Government;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
public interface GovernmentService {

    /**
     * 根据编号查询账号详细信息
     * @return
     */
    Government findGovernment();

    /**
     * 添加政府账号
     * @mbggenerated 2019-12-02
     */
    Government insertSelective(String commonwealArticle) throws BusinessException;

    /**
     * 根据政府编号根据信息
     * @mbggenerated 2019-12-02
     */
    Government updateByPrimaryKeySelective(String commonwealArticle);

    /**
     * 管理员查询文章列表
     * @return
     */
    PageInfoModel<List<Government>> findGovernmentList(Integer page, Integer size);
}

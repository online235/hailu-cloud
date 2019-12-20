package com.hailu.cloud.api.admin.module.xinan.service;

import com.hailu.cloud.api.admin.module.xinan.entity.XaHelpMember;
import com.hailu.cloud.api.admin.module.xinan.model.XaHelpMemberModel;
import com.hailu.cloud.common.model.page.PageInfoModel;

import java.util.List;

public interface XaHelpMenberService {

    /**
     * 查询方法
     * @param parameter
     * @return
     */
    List<XaHelpMemberModel> findListByParameter(Object parameter);

    /**
     * 插入方法
     * @param xaHelpMember
     * @return
     */
    Long insert(XaHelpMember xaHelpMember);

    /**
     * 更新方法
     * @param xaHelpMember
     * @return
     */
    int update(XaHelpMember xaHelpMember);


    /**
     * 分页操作
     * @param pageNum
     * @param size
     * @param parameter
     * @return
     */
    PageInfoModel<List<XaHelpMemberModel>> findListByParameterNewPage(Integer pageNum, Integer size, Object parameter);


}

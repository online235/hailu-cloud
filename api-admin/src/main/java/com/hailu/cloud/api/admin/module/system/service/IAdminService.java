package com.hailu.cloud.api.admin.module.system.service;

import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysAdminModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuzhijie
 */
public interface IAdminService {

    /**
     * 添加账号
     *
     * @param model
     */
    void addAccount(SysAdminModel model) throws BusinessException;

    /**
     * 删除账号
     *
     * @param id
     */
    void delAccount(Long id);

    /**
     * 修改自己的密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @throws BusinessException
     */
    void changePwd(String oldPwd, String newPwd) throws BusinessException;


    /**
     * 管理员去修改其他账号的密码
     *
     * @param id     账号ID
     * @param newPwd 新密码
     */
    void changePwdByAdmin(@Param("id") Long id, @Param("pwd") String newPwd);


    /**
     * 根据账号查询
     *
     * @param account
     * @param enableStatus
     * @return
     */
    SysAdminModel searchAccount(String account, int enableStatus);

    /**
     * 查询账号列表
     *
     * @param nickName     昵称
     * @param account      账号
     * @param enableStatus 启用状态
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return
     */
    PageInfoModel<List<SysAdminModel>> accountList(
            String nickName,
            String account,
            Integer enableStatus,
            Integer accountType,
            int pageNum,
            int pageSize);

    /**
     * 变更账号启用状态
     *
     * @param id           账号ID
     * @param enableStatus 启用状态
     */
    void changeStatus(Long id, int enableStatus) throws BusinessException;

    /**
     * 变更角色
     *
     * @param id      账号ID
     * @param roleIds 角色ID
     */
    void changeRoles(Long id, String roleIds) throws BusinessException;
}

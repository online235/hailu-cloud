package com.hailu.cloud.api.merchant.module.app.entity;

import java.io.Serializable;

public class McUser implements Serializable {
    /**
     * 编号
     */
    private String numberid;

    /**
     * 登陆账号
     */
    private String landingaccount;

    /**
     * 登陆密码
     */
    private String landingpassword;

    /**
     * 网络名称
     */
    private String networkname;

    /**
     * 账号类型
     */
    private String accounttype;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 创建时间
     */
    private Long createdat;

    /**
     * 更新时间
     */
    private Long updatedat;

    /**
     * mc_user
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     * @return numberid 编号
     */
    public String getNumberid() {
        return numberid;
    }

    /**
     * 编号
     * @param numberid 编号
     */
    public void setNumberid(String numberid) {
        this.numberid = numberid == null ? null : numberid.trim();
    }

    /**
     * 登陆账号
     * @return landingaccount 登陆账号
     */
    public String getLandingaccount() {
        return landingaccount;
    }

    /**
     * 登陆账号
     * @param landingaccount 登陆账号
     */
    public void setLandingaccount(String landingaccount) {
        this.landingaccount = landingaccount == null ? null : landingaccount.trim();
    }

    /**
     * 登陆密码
     * @return landingpassword 登陆密码
     */
    public String getLandingpassword() {
        return landingpassword;
    }

    /**
     * 登陆密码
     * @param landingpassword 登陆密码
     */
    public void setLandingpassword(String landingpassword) {
        this.landingpassword = landingpassword == null ? null : landingpassword.trim();
    }

    /**
     * 网络名称
     * @return networkname 网络名称
     */
    public String getNetworkname() {
        return networkname;
    }

    /**
     * 网络名称
     * @param networkname 网络名称
     */
    public void setNetworkname(String networkname) {
        this.networkname = networkname == null ? null : networkname.trim();
    }

    /**
     * 账号类型
     * @return accounttype 账号类型
     */
    public String getAccounttype() {
        return accounttype;
    }

    /**
     * 账号类型
     * @param accounttype 账号类型
     */
    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype == null ? null : accounttype.trim();
    }

    /**
     * 手机号码
     * @return phone 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 创建时间
     * @return createdat 创建时间
     */
    public Long getCreatedat() {
        return createdat;
    }

    /**
     * 创建时间
     * @param createdat 创建时间
     */
    public void setCreatedat(Long createdat) {
        this.createdat = createdat;
    }

    /**
     * 更新时间
     * @return updatedat 更新时间
     */
    public Long getUpdatedat() {
        return updatedat;
    }

    /**
     * 更新时间
     * @param updatedat 更新时间
     */
    public void setUpdatedat(Long updatedat) {
        this.updatedat = updatedat;
    }
}
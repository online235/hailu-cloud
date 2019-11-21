package com.hailu.cloud.api.mall.module.user.vo;

import lombok.Data;

/**
 * 实名认证
 *
 * @author Administrator
 */
@Data
public class RealNameVo {
    private String userId;//用户id
    private String userName;//用户名称
    private String idCard;//身份证号
    private String idcardImgx;//身份证正面照片
    private String idcardImgy;//身份证反面照片
    private int auditState;//审核状态 0 未审核  1已审核通过
    private int isSub;//是否提交认证信息
    private Long auditTime;

}

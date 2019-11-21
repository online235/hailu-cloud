package com.hailu.cloud.api.mall.module.ledger.enums;

/**
 * 审核状态
 *
 * @author Administrator
 */
public enum AuditStateEnum {
    NONE,
    // 通过
    PASS,
    // 拒绝
    REJECT;

    public static AuditStateEnum of(Integer state) {
        switch (state) {
            case 1:
                return PASS;
            case 2:
                return REJECT;
            default:
                return null;
        }
    }
}
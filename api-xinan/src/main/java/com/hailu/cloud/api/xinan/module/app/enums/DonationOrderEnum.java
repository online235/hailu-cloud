package com.hailu.cloud.api.xinan.module.app.enums;


/**
 * @Author: QiuFeng:WANG
 * @Description: 捐赠类型
 * @Date: 2019/12/5 0005
 * @program: cloud
 * @create: 2019-12-05 16:
 */
public enum DonationOrderEnum {

    /**
     * 政府慈善
     */
    GOVERNMENT_CHARITY,
    /**
     * 救助
     */
    RESCUE,
    /**
     * 互助
     */
    MUTUAL_AID;

    public static DonationOrderEnum of(int type) {
        switch (type) {
            case 1:
                return GOVERNMENT_CHARITY;
            case 2:
                return RESCUE;
            case 3:
                return MUTUAL_AID;
            default:
                return null;
        }
    }
}

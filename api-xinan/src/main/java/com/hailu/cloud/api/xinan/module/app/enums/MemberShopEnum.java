package com.hailu.cloud.api.xinan.module.app.enums;

import java.util.LinkedHashMap;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.xinan.enums
 * @Author: junpei.deng
 * @CreateTime: 2019-10-24 09:57
 * @Description: 会员商品类型
 */
public enum MemberShopEnum {
    INSURD(0,"参保"),
    MEMBER(1,"会员"),
    PROVIDER(2,"服务商");

    private final Integer key;
    private final String desc;
    private static final LinkedHashMap<Integer, String> MAP;

    static {
        MAP = new LinkedHashMap<>();
        for (MemberShopEnum bannerColumnEnum : MemberShopEnum.values()) {
            MAP.put(bannerColumnEnum.getKey(), bannerColumnEnum.getDesc());
        }
    }

    MemberShopEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static LinkedHashMap<Integer, String> getMap() {
        return MAP;
    }
}

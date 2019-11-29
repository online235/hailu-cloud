package com.hailu.cloud.api.mall.constant;

public final class Constant {
    //会员名id
    public static final String MEMBERID = "MEMBERID";

    //用户
    public static final String USER_INFORMATION = "USER_INFORMATION";

    //商户ID
    public static final String MCUSER_INFO = "numberId";

    /**
     * 省市区缓存
     */
    public static final String REDIS_NATION_CACHE = "redis:cache:nation:";

    /**
     * 旧省市区
     */
    public static final String REDIS_NATION_CACHE_OLD = "redis:cache:old:nation:";

    /**
     * 分享人key
     */
    public static final String REDIS_INVITATION_MEMBER_POVIDER_CACHE = "cache:invitationMember:poviderUserId:";

    /**
     *商品分享key
     */
    public static final String REDIS_INVITATION_MEMBER_GOODSIDANDUSERID_CACHE = "cache:invitationMember:GoodsIdAndUserId:";

    public static final int MINUTES = 60;
    public static final int HOUR = MINUTES * 60;
    public static final int DAY = HOUR * 24;
}

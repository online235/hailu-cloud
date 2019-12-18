package com.hailu.cloud.common.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.RandomUtil;
import com.hailu.cloud.common.enums.OrderNumberEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.Date;

/**
 * 订单号生成
 *
 * @author xuzhijie
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderNumberGenerator {

    private static final SecureRandom SECURE_RANDOM = RandomUtil.getSecureRandom();

    /**
     * 订单组成格式
     * 系统标识 - 业务标识 - 订单号生成时间(yyyyMMddHHmmss) - 用户ID(hash值) - 6位随机
     * 存储建议 varchar(45) 系统标识有可能会去到4-6的长度，预留一些长度
     *
     * @param type   标识类型
     * @param userId 用户ID
     * @return
     */
    public static String create(OrderNumberEnum type, String userId) {
        String randomCode = StringUtils.leftPad(String.valueOf(SECURE_RANDOM.nextInt(999999)), 6, "0");
        return type.getSystem() +
                type.getBusinessCode() +
                DateUtil.format(new Date(), DatePattern.PURE_DATETIME_FORMAT) +
                HashUtil.fnvHash(userId) +
                randomCode;
    }

}

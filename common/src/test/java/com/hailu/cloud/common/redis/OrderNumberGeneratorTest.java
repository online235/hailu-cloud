package com.hailu.cloud.common.redis;

import cn.hutool.core.util.HashUtil;
import com.hailu.cloud.common.enums.OrderNumberEnum;
import com.hailu.cloud.common.utils.OrderNumberGenerator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 订单号生成
 *
 * @author xuzhijie
 */
@Disabled
public class OrderNumberGeneratorTest {

    @Test
    public void test() {
        String userId = "10663360119039488";
        System.out.println(HashUtil.fnvHash(userId));
        System.out.println(OrderNumberGenerator.create(OrderNumberEnum.JD_JOIN_MEMBER, userId));
        System.out.println(OrderNumberGenerator.create(OrderNumberEnum.MS_JOIN_MEMBER, userId));
    }

}

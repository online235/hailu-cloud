package com.hailu.cloud.api.mall.module.goods.dao;

import com.hailu.cloud.api.mall.module.goods.entity.SerialNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 */
@Mapper
public interface SerialNumberMapper {
    /**
     * @Author HuangL
     * @Email huangl96@163.com
     * @Description 得到交易流水号
     * @Date 2019/1/25 11:32
     */
    SerialNumber verifySerialNumber(@Param("code") int code, @Param("serial") int serial);

    /**
     * @Author HuangL
     * @Email huangl96@163.com
     * @Description 更改占用的编号
     * @Date 2019/1/28 15:54
     */
    void updateSerialNumber(SerialNumber serialNumber);
}

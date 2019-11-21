package com.hailu.cloud.api.mall.module.goods.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.hailu.cloud.api.mall.module.goods.dao.SerialNumberMapper;
import com.hailu.cloud.api.mall.module.goods.entity.SerialNumber;
import com.hailu.cloud.api.mall.module.goods.service.ISerialNumberService;
import com.hailu.cloud.api.mall.module.goods.tool.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author HuangL
 * @Email huangl96@163.com
 * @Description 流水号处理类
 * @Date 2019/1/25 11:08
 */
@Service
public class SerialNumberServiceImpl implements ISerialNumberService {

    @Resource
    private SerialNumberMapper serialNumberMapper;

    @Override
    public SerialNumber verifySerialNumber(int code) {
        return verifySerialNumber1(code, 1).get(0);
    }

    /**
     * 统一请求单号类,
     *
     * @param code   编码
     * @param serial 数量
     * @return 返回多个单号
     */
    private List<SerialNumber> verifySerialNumber1(int code, int serial) {
        List<SerialNumber> list = new ArrayList<>();
        SerialNumber serialNumber = null;
        SerialNumber s = serialNumberMapper.verifySerialNumber(code, serial);
        String nowStr = DateUtils.getNow("yyyyMMdd");
        String sn = s.getSerialNumber().toString();
        String tempStr = sn.substring(0, 8);
        if (nowStr.equalsIgnoreCase(tempStr)) {
            String number = sn.substring(8, sn.length());
            for (int i = 0; i < serial; i++) {
                serialNumber = new SerialNumber();
                int n = serial - i;
                Long time = Long.parseLong(number) + (s.getSerial() - serial) + n;
                serialNumber.setSerialNumber(Long.parseLong(tempStr + time));
                serialNumber.setType(code + "");
                serialNumber.setSerial(serial);
                list.add(serialNumber);
            }
        } else {
            String number = "000001";
            String captcha = RandomUtil.randomNumbers(5);
            for (int i = 0; i < serial; i++) {
                serialNumber = new SerialNumber();
                serialNumber.setType(code + "");
                serialNumber.setSerial(serial);
                Long time = Long.parseLong(captcha + number) + (s.getSerial() - serial) + i;
                serialNumber.setSerialNumber(Long.parseLong(nowStr + time));
                list.add(serialNumber);
            }
        }
        return list;
    }

    @Override
    public List<SerialNumber> verifySerialNumber(int code, Integer goodsNum) {
        return verifySerialNumber1(code, goodsNum);
    }

    @Override
    public void updateSerialNumber(SerialNumber serialNumber) {
        serialNumberMapper.updateSerialNumber(serialNumber);
    }
}

package com.hailu.cloud.api.mall.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author leiqi
 * <p>
 * 系统静态变量
 */
@Component
public class Const {

    /**
     * 服務器項目地址
     */
    public static String SYSTEM_URL;
    /**
     * 服務器圖片地址
     */
    public static String PRO_URL;
    /**
     * 圖片上傳地址
     */
    public static  String IMAGES ;


    /**
     * 线程池数量
     */
    public static Integer THREAD_TOOL_NUM;

    /**
     * 提现阀值
     */
    public static Integer TRANSFEROUTTHRESHOLD;

    @Value("${income.transferout.threshold}")
    public void setTRANSFEROUTTHRESHOLD(Integer transferoutthreshold) {
        TRANSFEROUTTHRESHOLD = transferoutthreshold;
    }

    @Value("${sys.task.thread.pool.maxsize}")
    public void setTHREAD_TOOL_NUM(Integer thread_tool_num) {
        THREAD_TOOL_NUM = thread_tool_num;
    }



    @Value("${sys.url}")
    public void setSYSTEM_URL(String system_url) {
        SYSTEM_URL = system_url;
    }

    @Value("${sys.url}")
    public void setProUrl(String proUrl) {
        PRO_URL = proUrl;
    }

}

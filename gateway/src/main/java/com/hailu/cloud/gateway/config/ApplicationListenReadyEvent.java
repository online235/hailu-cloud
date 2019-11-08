package com.hailu.cloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 项目启动监听
 *
 * @author xuzhijie
 */
@Slf4j
@Component
public class ApplicationListenReadyEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    /**
     * 外部文件存储目录
     */
    @Value("${file.store.path:}")
    private String fileStorePath;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String ip = getLocalIp();
        log.info("Startup Successfully.");
        log.info("接口地址：http://{}:{}", ip, port);
        log.info("接口文档：http://{}:{}{}{}", ip, this.port, contextPath, "/swagger-ui.html");

        File file = new File(fileStorePath);
        boolean flag = file.mkdirs();
        if (flag) {
            log.warn("资源目录：目录无法创建");
        } else {
            log.info("资源目录：{}", file.getAbsolutePath());
        }
    }

    /**
     * 获取本机 IP
     *
     * @return
     */
    private String getLocalIp() {
        String ip = "127.0.0.1";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();
        } catch (UnknownHostException e) {
            // nothing to do ...
        }
        return ip;
    }

}

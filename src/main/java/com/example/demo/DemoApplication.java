package com.example.demo;

import com.example.demo.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhaoyue
 * @description:
 * @create 2020/4/24 11:49 上午
 **/
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.example.demo.filter"})
@RestController
@Slf4j
public class DemoApplication {
    public static void main(String[] args) {
        initMainThreadLogProperties();
        SpringApplication.run(DemoApplication.class, args);
    }

    private static void initMainThreadLogProperties() {
        PropertiesUtil propertiesUtil;
        try {
            propertiesUtil = new PropertiesUtil("application.properties");
            MDC.put("ip", getLocalIp());
            MDC.put("serverName", propertiesUtil.getProperty("spring.application.name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getLocalIp() {
        String localHost = null;
        try {
            localHost = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("getLocalIp() error", e);
            localHost = "0.0.0.0.0.1";
        }
        return localHost;
    }
}

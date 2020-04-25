package com.example.demo.util;


import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件工具类
 * @author zhao.Mr
 */
public class PropertiesUtil {

    private static Properties props;
    private final String resourceFile;

    public PropertiesUtil(String resourceFile) throws Exception {
        this.resourceFile = resourceFile;
        props = new Properties();
        try (InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(resourceFile)) {
            try (InputStreamReader reader = new InputStreamReader(is, "UTF-8")) {
                props.load(reader);
            }
        }
    }

    /**
     * 根据配置文件中的key获取value
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return value.trim();
    }

    /**
     * 根据配置文件中的key获取value (当获取不到值赋予默认值)
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value.trim();
    }
}
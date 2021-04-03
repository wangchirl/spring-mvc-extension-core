package com.shadow.utils;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author shadow
 * @create 2021-02-12
 * @description
 */
public class PropertiesUtils {

    public static String getString(String key, Resource resource) throws Exception {
        InputStream inputStream = resource.getInputStream();
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.getProperty(key);
    }
}

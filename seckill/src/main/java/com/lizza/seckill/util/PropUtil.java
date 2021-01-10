package com.lizza.seckill.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Desc:
 * @author: lizza1643@gmail.com
 * @date: 2021-01-09
 */
public class PropUtil {

    private static Properties properties = new Properties();

    static {
        InputStream is = PropUtil.class.getClassLoader().getResourceAsStream("redis.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringValue(String key) {
        return properties.getProperty(key);
    }

    public static int getIntValue(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}

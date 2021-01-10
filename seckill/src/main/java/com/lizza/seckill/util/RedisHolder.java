package com.lizza.seckill.util;

import redis.clients.jedis.Jedis;

/**
 * @Desc:
 * @author: lizza1643@gmail.com
 * @date: 2021-01-07
 */
public class RedisHolder {

    public static Jedis INSTANCE;
    static {
        INSTANCE = new Jedis(
                PropUtil.getStringValue("redis.host"),
                PropUtil.getIntValue("redis.port"));
        INSTANCE.auth(PropUtil.getStringValue("redis.password"));
        INSTANCE.select(PropUtil.getIntValue("redis.database"));
    }

}

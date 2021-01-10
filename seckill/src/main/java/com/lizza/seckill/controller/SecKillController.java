package com.lizza.seckill.controller;

import com.lizza.seckill.util.PropUtil;
import com.lizza.seckill.util.RedisHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @Desc:
 * @author: lizza1643@gmail.com
 * @date: 2021-01-09
 */
@RestController
public class SecKillController {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("secKill")
    public String secKill() {
        String userKey = "secKill:10000:user";
        String stockKey = "secKill:10000:stock";
        String userId = getUserId();
        Jedis redis = new Jedis(
                PropUtil.getStringValue("redis.host"),
                PropUtil.getIntValue("redis.port"));
        redis.auth(PropUtil.getStringValue("redis.password"));
        redis.select(PropUtil.getIntValue("redis.database"));

        // 活动未开始
        if (redis.get(stockKey) == null) {
            redis.close();
            return "活动还未开始";
        }

        // 用户已经参加过了
        if (redis.sismember(userKey, userId)) {
            redis.close();
            return "用户已经参加过了";
        }

        // 减库存, 加人
        if (redis.decr(stockKey) >= 0) {
            redis.sadd(userKey, userId);
            redis.close();
            return "用户 [" + userId + "] 秒杀成功";
        }

        redis.close();
        return "活动已结束";
    }

    public static String getUserId() {
        String result = new Random().nextInt(100) + "";
        while (result.length() < 2) {
            result = new Random().nextInt(100) + "";
        }

        return result;
    }

    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
            System.out.println(getUserId());
        }
    }
}

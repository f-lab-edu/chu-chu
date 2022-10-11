package com.example.chuchu.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void redis_test() {
        redisTemplate.opsForValue().set("abc", "1");
        redisTemplate.opsForValue().set("bcd", "2");

        System.out.println(redisTemplate.opsForValue().get("abc"));
        System.out.println(redisTemplate.opsForValue().get("bcd"));
    }

}

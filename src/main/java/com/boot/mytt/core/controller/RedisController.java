package com.boot.mytt.core.controller;

import com.boot.mytt.core.redis.util.JacksonRedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author renwq
 * @date 2021/1/2 14:35
 */
@RestController
public class RedisController {

    @Resource
    private JacksonRedisUtils jacksonRedisUtils;

    @RequestMapping("redisOps")
    public String redisOps(String str) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("key1_" + str, "value1" + str);
        hashMap.put("key2_" + str, "value2" + str);
        hashMap.put("key3_" + str, "value3" + str);
        hashMap.put("key4_" + str, "value4" + str);
        hashMap.put("key5_" + str, "value5" + str);
        hashMap.put("key6_" + str, "value6" + str);
        jacksonRedisUtils.hashPutAll("redisOps", hashMap);

        return "redisOps";
    }
}

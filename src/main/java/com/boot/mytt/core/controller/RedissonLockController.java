package com.boot.mytt.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.mytt.core.redisson.RedissonLockAnnotation;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author : JCccc
 * @CreateTime : 2020/5/13
 * @Description :
 **/
@RestController
public class RedissonLockController {
 
    @Autowired
    private RedissonClient redissonClient;
 
    @PostMapping(value = "testLock", consumes = "application/json")
    @RedissonLockAnnotation(lockRedisKey = "productName,platFormName")
    public String testLock(@RequestBody JSONObject params) {
        /**
         * 分布式锁key=params.getString("productName")+params.getString("platFormName");
         * productName 产品名称  platFormName 平台名称 如果都一致,那么分布式锁的key就会一直,那么就能避免并发问题
         */
        //TODO 业务处理

        try {
            System.out.println("接收到的参数："+params.toString());
            System.out.println("执行相关业务...");
            System.out.println("执行相关业务.....");

            System.out.println("执行相关业务......");

        } catch (Exception e) {
            System.out.println("已进行日志记录");
        }

        return "success";
    }

    @GetMapping("/testData")
    public void testData() {

        /****************************/
        // 插入 字符串
        RBucket<String> keyObj = redissonClient.getBucket("keyStr");
        keyObj.set("testStr", 300l, TimeUnit.SECONDS);

        //查询 字符串
        RBucket<String> keyGet = redissonClient.getBucket("keyStr");
        System.out.println(keyGet.get());

        /****************************/
        // 插入 list
        List<Integer> list = redissonClient.getList("list");
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        //查询 list
        List<Integer>  listGet = redissonClient.getList("list");
        System.out.println(listGet.toString());

        /****************************/
        //插入 map
        RMap<Object, Object> addMap = redissonClient.getMap("addMap");
        addMap.put("man1","a");
        addMap.put("man2","b");
        addMap.put("man3","c");

        //查询 map
        RMap<Object, Object> mapGet = redissonClient.getMap("addMap");
        System.out.println(mapGet.get("man1"));


        /****************************/
        //设置 set
        RSet<Object> testSet = redissonClient.getSet("testSet");
        testSet.add("S");
        testSet.add("D");
        testSet.add("F");
        testSet.add("G");

        //查询 set
        RSet<Object> setGet = redissonClient.getSet("testSet");
        System.out.println(setGet.readAll());
    }
}
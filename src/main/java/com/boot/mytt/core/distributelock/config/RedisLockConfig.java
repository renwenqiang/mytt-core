package com.boot.mytt.core.distributelock.config;

import com.boot.mytt.core.distributelock.RedisLock;
import com.boot.mytt.core.distributelock.RedisUtils;
import com.boot.mytt.core.distributelock.aspect.RedisLockAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Configuration
public class RedisLockConfig {

    @Resource
    private RedisTemplate<String, Object> jacksonRedisTemplate;

    /**
     * redis lock
     *
     * @return
     */
    @Bean
    public RedisUtils redisUtils() {
        return new RedisUtils(jacksonRedisTemplate);
    }

    /**
     * redis lock
     *
     * @return
     */
    @Bean
    public RedisLock redisLock() {
        return new RedisLock(redisUtils());
    }

    /**
     * distributed lock aspect
     *
     * @return
     */
    @Bean
    public RedisLockAspect redisLockAspect() {
        return new RedisLockAspect(redisLock());
    }
}

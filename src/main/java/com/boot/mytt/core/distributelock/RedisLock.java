package com.boot.mytt.core.distributelock;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class RedisLock extends AbstractRedisLock {

    private RedisUtils redisUtils;

    public RedisLock(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 线程安全类
     */
    private ThreadLocal<String> lockFlag = new ThreadLocal<>();

    /**
     * lock
     *
     * @param key         KEY
     * @param expire      过期时间
     * @param retryTimes  尝试获取锁次数
     * @param sleepMillis 休眠时间
     * @param reentrant   是否可重入
     * @return
     */
    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis, Boolean reentrant) {
        boolean result = redisLock(key, expire, reentrant);
        // 如果获取锁失败，按照传入的重试次数进行重试
        while ((!result) && retryTimes-- > 0) {
            try {
                log.debug("lock failed, retrying..." + retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = redisLock(key, expire, reentrant);
        }
        return result;
    }

    /**
     * lock set redis
     *
     * @param key       KEY
     * @param expire    过期时间
     * @param reentrant 是否可重入
     * @return
     */
    private boolean redisLock(String key, long expire, Boolean reentrant) {
        //可重入
        String requestId = lockFlag.get();
        if (null == lockFlag.get()) {
            requestId = UUID.randomUUID().toString();
        } else {
            String oldRequestId = redisUtils.get(key);
            if (reentrant && requestId.equals(oldRequestId)) {
                return true;
            }
        }
        lockFlag.set(requestId);
        return redisUtils.tryLock(key, requestId, expire, TimeUnit.MILLISECONDS);
    }

    /**
     * release lock
     *
     * @param key KEY
     * @return
     */
    @Override
    public boolean releaseLock(String key) {
        return redisUtils.releaseLock(key, lockFlag.get());
    }
}
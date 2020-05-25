package com.boot.mytt.core.distributelock;

/**
 * @author renwq
 * @date 2020/5/25
 */
public abstract class AbstractRedisLock {

    /**
     * KEY过期时间
     */
    long TIMEOUT_MILLIS = 30000;

    /**
     * 尝试获取锁次数
     */
    int RETRY_TIMES = 30;

    /**
     * 休眠时间
     */
    long SLEEP_MILLIS = 500;

    /**
     * 是否可重入
     */
    boolean REENTRANT = true;

    /**
     * @param key KEY
     * @return true|false
     */
    public boolean lock(String key) {
        return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS, REENTRANT);
    }

    /**
     * @param key        KEY
     * @param retryTimes 尝试次数
     * @return true|false
     */
    public boolean lock(String key, int retryTimes) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, SLEEP_MILLIS, REENTRANT);
    }

    /**
     * @param key         KEY
     * @param retryTimes  尝试获取锁次数
     * @param sleepMillis 休眠时间
     * @return true|false
     */
    public boolean lock(String key, int retryTimes, long sleepMillis) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, sleepMillis, REENTRANT);
    }

    /**
     * @param key    KEY
     * @param expire 过期时间
     * @return true|false
     */
    public boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_MILLIS, REENTRANT);
    }

    /**
     * @param key        KEY
     * @param expire     过期时间
     * @param retryTimes 尝试获取锁次数
     * @return true|false
     */
    public boolean lock(String key, long expire, int retryTimes) {
        return lock(key, expire, retryTimes, SLEEP_MILLIS, REENTRANT);
    }

    /**
     * 上锁
     *
     * @param key         KEY
     * @param expire      过期时间
     * @param retryTimes  尝试获取锁次数
     * @param sleepMillis 休眠时间
     * @return true|false
     */
    public abstract boolean lock(String key, long expire, int retryTimes, long sleepMillis, Boolean reentrant);

    /**
     * 解锁
     *
     * @param key KEY
     * @return true|false
     */
    public abstract boolean releaseLock(String key);
}

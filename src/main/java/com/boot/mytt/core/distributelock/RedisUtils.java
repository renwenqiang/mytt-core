package com.boot.mytt.core.distributelock;


import com.boot.mytt.core.exception.SystemException;
import com.boot.mytt.core.util.CharsetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class RedisUtils {

    private RedisTemplate<String, Object> redisTemplate;

    public static final String UNLOCK_LUA;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        if (Objects.isNull(redisTemplate)) {
            throw new SystemException("redisTemplate is null");
        }
        this.redisTemplate = redisTemplate;
    }

    /**
     * 释放锁脚本，原子操作
     */
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }


    /**
     * 获取分布式锁，原子操作
     *
     * @param lockKey
     * @param requestId 唯一ID
     * @param expire
     * @param timeUnit
     * @return
     */
    public boolean tryLock(String lockKey, String requestId, long expire, TimeUnit timeUnit) {
        try {
            RedisCallback<Boolean> callback = (connection) -> {
                return connection.set(lockKey.getBytes(CharsetUtils.CHARSET_UTF_8), requestId.getBytes(CharsetUtils.CHARSET_UTF_8), Expiration.seconds(timeUnit.toSeconds(expire)), RedisStringCommands.SetOption.SET_IF_ABSENT);
            };
            return (Boolean) redisTemplate.execute(callback);
        } catch (Exception e) {
            log.error("redis lock error.", e);
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @param requestId 唯一ID
     * @return
     */
    public boolean releaseLock(String lockKey, String requestId) {
        RedisCallback<Boolean> callback = (connection) -> {
            return connection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(CharsetUtils.CHARSET_UTF_8), requestId.getBytes(CharsetUtils.CHARSET_UTF_8));
        };
        return (Boolean) redisTemplate.execute(callback);
    }

    /**
     * 获取Redis锁的value值
     *
     * @param lockKey
     * @return
     */
    public String get(String lockKey) {
        try {
            RedisCallback<String> callback = (connection) -> {
                byte[] bytes = connection.get(lockKey.getBytes());
                if (bytes == null || bytes.length == 0) {
                    return null;
                } else {
                    return new String(bytes, CharsetUtils.CHARSET_UTF_8);
                }
            };
            return redisTemplate.execute(callback);
        } catch (Exception e) {
            log.error("get redis occurred an exception", e);
        }
        return null;
    }
}

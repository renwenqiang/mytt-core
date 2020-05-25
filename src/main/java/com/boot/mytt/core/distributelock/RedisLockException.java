package com.boot.mytt.core.distributelock;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class RedisLockException extends RuntimeException {

    private static final long serialVersionUID = -5447864456654718164L;

    public RedisLockException(String message) {
        super(message);
    }

    public RedisLockException(String message, Throwable cause) {
        super(message, cause);
    }
}

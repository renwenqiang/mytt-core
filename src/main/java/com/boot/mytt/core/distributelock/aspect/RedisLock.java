package com.boot.mytt.core.distributelock.aspect;

import java.lang.annotation.*;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

    /**
     * 锁的资源，key。支持spring El表达式 以#开头获取参数，多个情况下以,分割
     */
    String value();

    /**
     * 锁KEY的前缀
     */
    String prefix() default "";

    /**
     * 持锁时间,单位毫秒
     */
    long keepMills() default 30000;

    /**
     * 重试的间隔时间,设置GIVEUP忽略此项
     */
    long sleepMills() default 200;

    /**
     * 重试次数
     */
    int retryTimes() default 5;

    /**
     * 未获取到锁是否抛出异常
     *
     * @return
     */
    boolean rollback() default true;

    /**
     * 重入锁
     *
     * @return
     */
    boolean reentrant() default true;
}

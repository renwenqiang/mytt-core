package com.boot.mytt.core.distributelock.aspect;


import com.boot.mytt.core.distributelock.RedisLockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Aspect
@Slf4j
public class RedisLockAspect {

    private com.boot.mytt.core.distributelock.RedisLock redisLock;

    public RedisLockAspect(com.boot.mytt.core.distributelock.RedisLock redisLock) {
        if (Objects.isNull(redisLock)) {
            throw new RedisLockException("redisLock is null");
        }
        this.redisLock = redisLock;
    }

    private ExpressionParser parser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("@annotation(com.boot.mytt.core.distributelock.aspect.RedisLock)")
    private void lockPoint() {

    }

    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature msig = (MethodSignature) pjp.getSignature();
        Object target = pjp.getTarget();
        Method method = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        StringBuffer key = new StringBuffer();
        key.append(redisLock.prefix());
        Object[] args = pjp.getArgs();
        //若EL表达式需解析value
        if (redisLock.value().startsWith("#")) {
            String[] values = redisLock.value().split(",");
            for (String v : values) {
                String keyValue = parse(v, method, args);
                if (StringUtils.isNotEmpty(keyValue)) {
                    key.append(keyValue);
                }
                log.info(keyValue);
            }
        } else {
            key.append(redisLock.value());
        }
        try {
            //获取锁
            boolean lock = this.redisLock.lock(key.toString(), redisLock.keepMills(), redisLock.retryTimes(), redisLock.sleepMills(), redisLock.reentrant());
            //未获取锁 则检验是否需要抛出异常(默认抛出异常)
            if (!lock) {
                log.debug("get lock failed : " + key.toString());
                //判断是否抛出异常
                if (redisLock.rollback()) {
                    throw new RedisLockException("get lock failed : " + key.toString());
                } else {
                    return null;
                }
            } else {
                //得到锁,执行方法
                log.debug("get lock success : " + key.toString());
                return pjp.proceed();
            }
        } catch (Exception e) {
            throw new RedisLockException("lock fail", e);
        } finally {
            //释放锁
            boolean releaseResult = this.redisLock.releaseLock(key.toString());
            log.debug("release lock : " + key.toString() + (releaseResult ? " success" : " failed"));
        }
    }

    /**
     * @param key
     * @param method
     * @param args
     * @return
     * @author wuxj
     * @date 2018/1/18 17:19
     */
    private String parse(String key, Method method, Object[] args) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}

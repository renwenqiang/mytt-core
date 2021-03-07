package com.boot.mytt.core.thread.chap05;

/**
 * @author renwq
 * @date 2021/3/7 下午4:57
 */
public interface TaskRunnerSpec {

    void init();
    void submit(Runnable runnable) throws InterruptedException;
}

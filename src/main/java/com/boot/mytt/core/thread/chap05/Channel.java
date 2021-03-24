package com.boot.mytt.core.thread.chap05;

/**
 * @author renwq
 * @date 2021/3/7 上午10:47
 */
public interface Channel<P> {

    void put(P product) throws InterruptedException;

    P take() throws InterruptedException;
}

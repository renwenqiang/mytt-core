package com.boot.mytt.core.thread.chap03;

/**
 * @author renwq
 * @date 2021/2/27 上午11:40
 */
public class Counter {
    private volatile long count;

    public long getCount() {
        return count;
    }

    public void increment() {
        synchronized (this) {
            count++;
        }
    }
}

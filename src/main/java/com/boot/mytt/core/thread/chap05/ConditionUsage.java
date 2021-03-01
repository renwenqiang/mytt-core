package com.boot.mytt.core.thread.chap05;

import java.nio.charset.CoderMalfunctionError;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author renwq
 * @date 2021/3/1 下午10:46
 */
public class ConditionUsage {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private volatile boolean flag = false;

    public void aGuaredMethod() throws InterruptedException {
        lock.lock();
        try {
            while (flag) {
                condition.await();
            }
            doAction();
        } finally {
            lock.unlock();
        }
    }

    private void doAction() {
        System.out.println();
    }

    public void anNotificationMethod() {
        lock.lock();
        try {
            changeState();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    private void changeState() {
        System.out.println();
    }
}

package com.boot.mytt.core.thread.chap05;

import com.boot.mytt.core.thread.util.Debug;
import com.boot.mytt.core.thread.util.Tools;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author renwq
 * @date 2021/3/2 下午11:08
 */
public class TimeoutWaitWithCondition {

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean ready = false;
    protected static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    lock.lock();
                    try {
                        ready = random.nextInt(1100) < 5 ? true : false;
                        if (ready) {
                            condition.signal();
                        }
                        Tools.randomPause(500);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        writer(1000);
    }

    public static void writer(final long timeOUt) throws InterruptedException {
        if (timeOUt < 0) {
            throw new IllegalArgumentException();
        }
        final Date dealTime = new Date(System.currentTimeMillis() + timeOUt);
        boolean continueToWait = true;
        lock.lock();
        try {
            while (!ready) {
                Debug.info("still not ready, continue to wait %s.", continueToWait);
                if (!continueToWait) {
                    Debug.info("Wait time out, unable to execution target action.");
                    return;
                }
                continueToWait = condition.awaitUntil(dealTime);
            }
            guarededAction();
        } finally {
            lock.unlock();
        }
    }

    private static void guarededAction() {
        Debug.info("Take some action.");
    }
}

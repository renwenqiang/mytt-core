package com.boot.mytt.core.thread.chap05;

import com.boot.mytt.core.util.Debug;
import com.boot.mytt.core.util.Tools;

import java.util.Random;

/**
 * @author renwq
 * @date 2021/3/1 下午11:22
 */
public class TimeoutWaitExample {

    private static final Object lock = new Object();
    private static boolean ready = false;
    protected static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    synchronized (lock) {
                        ready = RANDOM.nextInt(100) < 20 ? true : false;
                        if (ready) {
                            lock.notify();
                        }
                    }
                    Tools.randomPause(500);
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public static void writer(final long timeOut) throws InterruptedException {
        if (timeOut < 0L) {
            throw new IllegalArgumentException();
        }
        long start = System.currentTimeMillis();
        long waitTime;
        long now;
        synchronized (lock) {
            while (!ready) {
                now = System.currentTimeMillis();
                waitTime = timeOut - (now - start);
                Debug.info("Remaining time to wait: %s ms", waitTime);
                if (waitTime <= 0) {
                    break;
                }
                lock.wait(waitTime);
                if (ready) {
                    guardedAction();
                } else {
                    Debug.info("Wait timed out, unable to execution target action.");
                }
            }
        }
    }

    public static void guardedAction() {
        Debug.info("Take some action.");
    }
}

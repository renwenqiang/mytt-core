package com.boot.mytt.core.thread.chap05;

import com.boot.mytt.core.thread.util.Debug;
import com.boot.mytt.core.thread.util.Tools;

import java.util.concurrent.CountDownLatch;

/**
 * @author renwq
 * @date 2021/3/3 下午9:50
 */
public class CountDownLatchExample {

    private static final CountDownLatch latch = new CountDownLatch(4);
    private static int data;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int k=0; k<10; k++) {
                    data = k;
                    latch.countDown();
                    Tools.randomPause(1000);
                }
            }
        };
        thread.start();
        latch.await();
        Debug.info("It is done. data=%d", data);
    }
}

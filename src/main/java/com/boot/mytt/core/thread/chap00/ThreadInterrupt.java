package com.boot.mytt.core.thread.chap00;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renwq
 * @date 2021/5/21 11:28
 */
public class ThreadInterrupt implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadInterrupt.class);

    boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        LOGGER.info("main启动...");
        Thread thread = new Thread(new ThreadInterrupt(), "My thread");
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        LOGGER.info("interrupt状态: {}...", thread.isInterrupted());
        Thread.sleep(2000);
        LOGGER.info("main运行完结...");
    }
    @Override
    public void run() {
        while (!stop) {
            LOGGER.info("running...");
            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < 1000L) {

            }
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        LOGGER.info("thread exit under interrupt...");
    }
}

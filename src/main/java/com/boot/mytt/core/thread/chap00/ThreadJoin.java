package com.boot.mytt.core.thread.chap00;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renwq
 * @date 2021/5/21 11:28
 */
public class ThreadJoin implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadJoin.class);

    boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        LOGGER.info("main启动...");
        Thread thread = new Thread(new ThreadJoin(), "My thread");
        thread.start();
    }
    @Override
    public void run() {
        while (!stop) {
            LOGGER.info("running...");
            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < 1000L) {

            }
        }
        LOGGER.info("thread exit under interrupt...");
    }
}

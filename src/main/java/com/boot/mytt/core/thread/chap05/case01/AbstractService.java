package com.boot.mytt.core.thread.chap05.case01;

import com.boot.mytt.core.thread.util.Debug;

import java.util.concurrent.CountDownLatch;

/**
 * @author renwq
 * @date 2021/3/3 下午10:09
 */
public abstract class AbstractService implements Service {

    protected boolean started = false;
    protected final CountDownLatch countDownLatch;

    public AbstractService(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void start() {
        new ServiceStarter().start();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return started;
    }

    protected abstract void doStart() throws Exception;

    class ServiceStarter extends Thread{
        @Override
        public void run() {
            final String serviceName = AbstractService.this.getClass().getSimpleName();
            Debug.info("Starting %s...", serviceName);
            try {
                doStart();
                started = true;
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
                Debug.info("Done starting %s", serviceName);
            }
        }
    }
}

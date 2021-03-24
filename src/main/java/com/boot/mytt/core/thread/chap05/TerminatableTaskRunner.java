package com.boot.mytt.core.thread.chap05;

import com.boot.mytt.core.thread.util.Debug;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author renwq
 * @date 2021/3/7 下午4:56
 */
public class TerminatableTaskRunner implements TaskRunnerSpec {

    protected final BlockingQueue<Runnable> channel;
    protected volatile boolean isUse = true;
    public final AtomicLong reservations = new AtomicLong(0);

    public volatile Thread workThread;

    public TerminatableTaskRunner(BlockingQueue<Runnable> channel) {
        this.channel = channel;
        this.workThread = new WorkThread();
    }

    public TerminatableTaskRunner() {
        this(new LinkedBlockingDeque<Runnable>());
    }



    @Override
    public void init() {
        final Thread t = workThread;
        if (null != t) {
            t.start();
        }
    }

    @Override
    public void submit(Runnable runnable) throws InterruptedException {
        channel.put(runnable);
        reservations.incrementAndGet();
    }

    public void shutdown() {
        Debug.info("shutdown service...");
        isUse = false;
        final Thread t = workThread;
        if (null != t) {
            t.interrupt();
        }
    }

    class WorkThread extends Thread{
        @Override
        public void run() {
            Runnable task = null;
            try {
                for (;;) {
                    if (!isUse && reservations.get() <= 0) {
                        break;
                    }
                    task = channel.take();
                    try {
                        task.run();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    reservations.decrementAndGet();
                }
            } catch (InterruptedException e) {
                workThread = null;
            }
            Debug.info("worker thread terminated.");
        }
    }
}

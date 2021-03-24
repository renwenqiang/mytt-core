package com.boot.mytt.core.thread.chap05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author renwq
 * @date 2021/3/7 下午3:37
 */
public class TaskRunner {
    protected final BlockingQueue<Runnable> queue;
    protected volatile Thread workThread;

    public TaskRunner(BlockingQueue<Runnable> queue) {
        this.queue = queue;
        this.workThread = new WorkThread();
    }

    public TaskRunner() {
        this(new LinkedBlockingDeque<Runnable>());
    }

    public void init() {
        final Thread t = workThread;
        if (null != t) {
            t.start();
        }
    }

    public void submit(Runnable task) throws InterruptedException{
        queue.put(task);
    }

    class WorkThread extends Thread{
        @Override
        public void run() {
            Runnable task = null;
            try {
                for (;;) {
                    task = queue.take();
                    try {
                        task.run();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

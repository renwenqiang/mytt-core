package com.boot.mytt.core.thread.chap05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author renwq
 * @date 2021/3/7 上午10:59
 */
public class SemaphoreBaseChannel<P> implements Channel<P> {

    private final BlockingQueue<P> queue;
    private final Semaphore semaphore;

    public SemaphoreBaseChannel(BlockingQueue<P> queue, int flowLimit) {
        this(queue, flowLimit, false);
    }

    public SemaphoreBaseChannel(BlockingQueue<P> queue, int flowLimit, boolean isFair) {
        this.queue = queue;
        this.semaphore = new Semaphore(flowLimit, isFair);
    }

    @Override
    public void put(P product) throws InterruptedException {
        semaphore.acquire();
        try {
            queue.put(product);
        } finally {
            semaphore.release();
        }
    }

    @Override
    public P take() throws InterruptedException {
        return queue.take();
    }
}

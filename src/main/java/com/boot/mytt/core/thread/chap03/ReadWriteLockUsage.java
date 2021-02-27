package com.boot.mytt.core.thread.chap03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author renwq
 * @date 2021/2/27 上午12:37
 */
public class ReadWriteLockUsage {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void read() {
        readLock.lock();
        try {
            System.out.println();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        writeLock.lock();
        try {
            System.out.println();
        } finally {
            writeLock.unlock();
        }
    }
}

package com.boot.mytt.core.thread.chap03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author renwq
 * @date 2021/2/27 上午8:47
 */
public class ReadWriteLockDownGrade {

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void operationWithLockDownGrade() {
        boolean readLockAcquired = false;
        writeLock.lock();
        try {
            System.out.println();
            readLock.tryLock();
            readLockAcquired = true;
        } finally {
            writeLock.unlock();
        }
        if (readLockAcquired) {
            try {
                System.out.println();
            } finally {
                readLock.unlock();
            }
        }
    }
}

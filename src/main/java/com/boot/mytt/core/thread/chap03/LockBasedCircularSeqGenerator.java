package com.boot.mytt.core.thread.chap03;

import com.boot.mytt.core.thread.chap02.CircularSeqGenerator;
import org.apache.ibatis.jdbc.SQL;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author renwq
 * @date 2021/2/27 上午12:20
 */
public class LockBasedCircularSeqGenerator implements CircularSeqGenerator {

    private short sequence = -1;
    private final Lock lock = new ReentrantLock();

    @Override
    public short nextSequence() {
        lock.lock();
        try {
            if (sequence >= 999) {
                sequence = 0;
            } else {
                sequence++;
            }
            return sequence;
        } finally {
            lock.unlock();
        }
    }
}

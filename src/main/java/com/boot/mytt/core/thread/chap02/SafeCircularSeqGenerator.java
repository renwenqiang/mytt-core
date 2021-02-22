package com.boot.mytt.core.thread.chap02;

/**
 * @author renwq
 * @date 2021/2/22 下午10:30
 */
public class SafeCircularSeqGenerator implements CircularSeqGenerator {

    private short sequence = -1;

    @Override
    public synchronized short nextSequence() {
        if (sequence >= 999) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }
}

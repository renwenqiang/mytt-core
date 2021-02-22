package com.boot.mytt.core.thread.chap02;

/**
 * @author renwq
 * @date 2021/2/22 下午10:23
 */
public class NoRaceCondition {

    // 以下语句使用的是局部变量而非状态变量，并不会产生竞态
    public int nextSequence(int sequence) {
        if (sequence >= 999) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }
}

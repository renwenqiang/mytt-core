package com.boot.mytt.core.thread.chap07;

import com.boot.mytt.core.thread.util.Debug;

/**
 * @author renwq
 * @date 2021/3/21 13:43
 */
public class DeadlockingPhilosopher extends AbstractPhilosopher {

    public DeadlockingPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (left) {
            Debug.info("%s is picking up %s on his left... %n", this, left);
            left.pickUp();
            synchronized (right) {
                Debug.info("%s is picking up %s on his right... %n", this, right);
                right.pickUp();
                doEat();
                right.putDown();
            }
            left.putDown();
        }
    }
}

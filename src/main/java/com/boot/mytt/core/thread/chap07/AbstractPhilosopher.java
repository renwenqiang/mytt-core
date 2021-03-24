package com.boot.mytt.core.thread.chap07;

import com.boot.mytt.core.thread.util.Debug;
import com.boot.mytt.core.thread.util.Tools;

/**
 * @author renwq
 * @date 2021/3/21 10:23
 */
public abstract class AbstractPhilosopher extends Thread implements Philosopher {

    protected final int id;
    protected final Chopstick left;
    protected final Chopstick right;

    public AbstractPhilosopher(int id, Chopstick left, Chopstick right) {
        super("Philosopher - " + id);
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void think() {
        Debug.info("%s is thinking... %n", this);
        Tools.randomPause(20);
    }

    protected void doEat() {
        Debug.info("%s is eating... %n", this);
        Tools.randomPause(20);
    }

    @Override
    public abstract void eat();
}

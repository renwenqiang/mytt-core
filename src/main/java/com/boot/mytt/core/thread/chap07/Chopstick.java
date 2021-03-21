package com.boot.mytt.core.thread.chap07;

/**
 * @author renwq
 * @date 2021/3/21 10:15
 */
public class Chopstick {
    private final int id;
    private Status status = Status.PUT_DOWN;
    public static enum Status{
        PICKED_UP,
        PUT_DOWN;
    }

    public Chopstick(int id) {
        super();
        this.id = id;
    }

    public void pickUp() {
        status = Status.PICKED_UP;
    }

    public void putDown() {
        status = Status.PUT_DOWN;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id +
                '}';
    }
}

package com.boot.mytt.core.design.chap10;

/**
 * @author renwq
 * @since 2020/7/4 23:59
 */
public abstract class Humer {
    protected abstract void start();

    protected boolean isAlerm() {
        return false;
    }

    final public void run() {
        System.out.println("------");
        System.out.println(isAlerm());
    }
}

package com.boot.mytt.core.thread.chap01;

/**
 * @author renwq
 * @date 2021/2/18 下午10:57
 */
public class WelcomeApp1 {
    public static void main(String[] args) {
        Thread welcomeApp1 = new Thread(new WelcomeThread1());
        welcomeApp1.start();

        System.out.printf("1.Welcome, I am %s.%n", Thread.currentThread().getName());
    }
}

class WelcomeThread1 implements Runnable {
    @Override
    public void run() {
        System.out.printf("2.Welcome, I am %s.%n", Thread.currentThread().getName());
    }
}

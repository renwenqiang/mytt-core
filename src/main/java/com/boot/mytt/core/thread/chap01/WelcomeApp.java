package com.boot.mytt.core.thread.chap01;

/**
 * @author renwq
 * @date 2021/2/18 下午10:43
 */
public class WelcomeApp {

    public static void main(String[] args) {
        WelcomeThread welcomeThread = new WelcomeThread();
        welcomeThread.start();

        System.out.printf("1.Welcome, I am %s.%n", Thread.currentThread().getName());
    }

}
class WelcomeThread extends Thread{
    @Override
    public void run() {
        System.out.printf("2.Welcome, I am %s.%n", Thread.currentThread().getName());
    }
}

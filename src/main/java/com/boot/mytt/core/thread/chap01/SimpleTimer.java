package com.boot.mytt.core.thread.chap01;

/**
 * @author renwq
 * @date 2021/2/19 下午11:55
 */
public class SimpleTimer {
    private static int count;

    public static void main(String[] args) {
        count = 10;
        int remaining;
        while (true) {
            remaining = countDown();
            if (0 == remaining) {
                break;
            } else {
                System.out.printf("Remaining %s second(s). %n", remaining);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    private static int countDown() {
        return count--;
    }
}

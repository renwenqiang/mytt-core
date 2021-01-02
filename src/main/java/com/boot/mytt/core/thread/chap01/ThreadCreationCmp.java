package com.boot.mytt.core.thread.chap01;

public class ThreadCreationCmp {

    public static void main(String[] args) {
        Thread t;
        CountingTask ct = new CountingTask();
        final int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        for (int k=0; k<8; k++) {
            t = new Thread(ct);
            t.start();
        }
        for (int k=0; k<8; k++) {
            t = new CountingThead();
            t.start();
        }
    }

    static class Counter{
        private int count = 0;
        public void increment() {
            count++;
        }

        public int value() {
            return count;
        }
    }
    
    static class CountingTask implements Runnable{

        private Counter counter = new Counter();
        @Override
        public void run() {
            for (int k=0; k<100; k++) {
                doSomeThing();
                counter.increment();
            }
            System.out.println("CounterTask: " + counter.value());
        }

        private void doSomeThing() {
            try {
                Thread.sleep(80);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class CountingThead extends Thread{
        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int k=0; k<100; k++) {
                doSomeThing();
                counter.increment();
            }
            System.out.println("CountingThead: " + counter.value());
        }

        private void doSomeThing() {
            try {
                Thread.sleep(80);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

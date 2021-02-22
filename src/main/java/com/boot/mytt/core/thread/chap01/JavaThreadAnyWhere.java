package com.boot.mytt.core.thread.chap01;

/**
 * @author renwq
 * @date 2021/2/20 下午10:15
 */
public class JavaThreadAnyWhere {

    static class Helper implements Runnable {

        private final String message;

        public static void main(String[] args) {
            // 获取当前线程
            Thread currentThread = Thread.currentThread();
            // 获取当前线程的名称
            String currentThreadName = currentThread.getName();
            System.out.printf("The main method was execute by thread: %s. %n", currentThreadName);
            Helper helper = new Helper("Java Thread AnyWhere");
            Thread thread = new Thread(helper);
            thread.start();
        }

        public Helper(String message) {
            this.message = message;
        }

        private void doSomeThing(String message) {
            // 获取当前线程
            Thread currentThread = Thread.currentThread();
            // 获取当前线程的名称
            String currentThreadName = currentThread.getName();
            Thread.State state = currentThread.getState();
            System.out.printf("The doSomeThing method was execute by thread: %s. %n", currentThreadName);
            System.out.println("Do thing with: " + message);
            System.out.println("Thread.State: " + state);
            // 线程状态的几个枚举值：
            // NEW 已创建，未启动的线程
            // RUNNABLE 复合状态 包含：READY RUNNING
            // BLOCKED
            // WAITING
            // TIMED_WAITING
            // TERMINATED
        }

        @Override
        public void run() {
            doSomeThing(message);
        }
    }
}

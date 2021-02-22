package com.boot.mytt.core.thread.chap02;

import com.boot.mytt.core.util.Tools;

/**
 * @author renwq
 * @date 2021/2/21 下午2:16
 */
public class RaceConditionDemo {

    public static void main(String[] args) {
        Thread[] workerThreads = new Thread[4];
        for (int k=0; k<workerThreads.length; k++) {
            workerThreads[k] = new WorkerThread(k, 10);
        }
        for (Thread thread: workerThreads) {
            thread.start();
        }
    }

    static class WorkerThread extends Thread{

        private final int requestCount;

        public WorkerThread(int id, int requestCount) {
            super("Worker" + id);
            this.requestCount = requestCount;
        }
        @Override
        public void run() {
            int i = requestCount;
            String requestID;
            RequestIDGenerator requestIDGenerator = RequestIDGenerator.getInstance();
            while (i-- > 0) {
                // 生成Request ID
                requestID = requestIDGenerator.nextID();
                processRequest(requestID);
            }
        }

        // 模拟请求处理
        private void processRequest(String requestID) {
            // 模拟请求处理耗时
            Tools.randomPause(50);
            System.out.printf("%s got requestID %s %n", Thread.currentThread().getName(), requestID);
        }
    }
}

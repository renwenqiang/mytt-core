package com.boot.mytt.core.thread.chap05;

import com.boot.mytt.core.thread.util.Debug;
import com.boot.mytt.core.thread.util.Tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author renwq
 * @date 2021/3/6 上午11:24
 */
public class ShootPractice {

    final Soldier[][] rank;
    final int N;
    final int lasting;
    volatile boolean done = false;
    volatile int nextLine = 0;
    final CyclicBarrier shiftBarrier;
    final CyclicBarrier startBarrier;

    public static void main(String[] args) throws InterruptedException {
        ShootPractice sp = new ShootPractice(4, 5, 24);
        sp.start();
    }

    public ShootPractice(int N, int lineCount, int lasting) {
        this.N = N;
        this.lasting = lasting;
        this.rank = new Soldier[lasting][N];
        for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < N; j++) {
                rank[i][j] = new Soldier(i * N + j);
            }
        }
        shiftBarrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                nextLine = (nextLine + 1) % lineCount;
                Debug.info("Next turn is :%d", nextLine);
            }
        });
        startBarrier = new CyclicBarrier(N);
    }

    public void start() throws InterruptedException {
        Thread[] threads = new Thread[N];
        for (int i = 0; i < N; ++i) {
            threads[i] = new Shooting(i);
            threads[i].start();
        }
        // 指定时间后停止打靶
        Thread.sleep(lasting * 1000);
        stop();
        for (Thread t : threads) {
            t.join();
        }
        Debug.info("Practice finished.");
    }

    public void stop() {
        done = true;
    }

    class Shooting extends Thread{

        final int index;

        public Shooting(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            Soldier soldier;
            try {
                while (!done) {
                    soldier = rank[nextLine][index];
                    // 一排中的士兵必须同时开始射击
                    startBarrier.await();// 语句③
                    // 该士兵开始射击
                    soldier.fire();
                    // 一排中的士兵必须等待该排中的所有其他士兵射击完毕才能够离开射击点
                    shiftBarrier.await();// 语句④
                }
            } catch (InterruptedException e) {
                // 什么也不做
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class Soldier{
        private final int seqNo;

        public Soldier(int seqNo) {
            this.seqNo = seqNo;
        }

        public void fire() {
            Debug.info(this + " starting firing...");
            Tools.randomPause(200);
            Debug.info(this + " fired...");
        }

        @Override
        public String toString() {
            return "Soldier-" +seqNo ;
        }
    }
}

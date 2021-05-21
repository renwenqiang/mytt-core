package com.boot.mytt.core.thread.chap00;

import lombok.SneakyThrows;

import java.util.Scanner;

/**
 * @author renwq
 * @date 2021/3/24 14:33
 */
public class DaemonDemo {

    public static void main(String[] args) {
        Thread daemonThread = new Thread(new DaemonRunner());
        daemonThread.setDaemon(true);
        daemonThread.start();
        System.out.println("isDaemon? = " + daemonThread.isDaemon());
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("JVM Exit!");
            }
        });
    }

    static class DaemonRunner implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                for (int k=0; k<10; k++) {
                    System.out.println("Daemon thread " + k);
                    Thread.sleep(1000);
                }
                break;
            }
        }
    }
}

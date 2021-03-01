package com.boot.mytt.core.thread.chap05;

import com.boot.mytt.core.thread.util.Debug;
import com.boot.mytt.core.thread.util.Tools;
import lombok.SneakyThrows;

import java.util.Random;

/**
 * @author renwq
 * @date 2021/3/1 下午11:48
 */
public class AlarmAgent {

    private final static AlarmAgent INSTANCE = new AlarmAgent();
    private boolean connectToServer = false;
    private HeartbeatThread heartbeatThread = new HeartbeatThread();

    private AlarmAgent() {

    }

    public static AlarmAgent getInstance() {
        return INSTANCE;
    }

    public void init() {
        connectedToServer();
        heartbeatThread.setDaemon(true);
        heartbeatThread.start();
    }

    private void connectedToServer() {
        new Thread() {
            @Override
            public void run() {
                doConnect();
            }
        }.start();
    }

    private void doConnect() {
        Tools.randomPause(500);
        synchronized (this) {
            connectToServer = true;
            notify();
        }
    }

    class HeartbeatThread extends Thread{
        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(1000);
            while (true) {
                if (checkConnection()) {
                    connectToServer = true;
                } else {
                    connectToServer = false;
                    Debug.info("Alarm Agent was disconnected from server.");
                    connectedToServer();
                }
                Thread.sleep(2000);
            }
        }

        private boolean checkConnection() {
            boolean isConnected = true;
            final Random random = new Random();
            int rand = random.nextInt(1000);
            if (rand < 500) {
                isConnected = false;
            }
            return isConnected;
        }
    }
}

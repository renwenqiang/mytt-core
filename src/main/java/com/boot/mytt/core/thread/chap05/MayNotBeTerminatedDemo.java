package com.boot.mytt.core.thread.chap05;

import com.boot.mytt.core.thread.util.Debug;
import lombok.SneakyThrows;

/**
 * @author renwq
 * @date 2021/3/7 下午4:51
 */
public class MayNotBeTerminatedDemo {
    public static void main(String[] args) throws InterruptedException {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.init();
        taskRunner.submit(new Runnable() {
            @Override
            public void run() {
                Debug.info("before doing task.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
                Debug.info("after doing task.");
            }
        });
        taskRunner.workThread.interrupt();
    }
}

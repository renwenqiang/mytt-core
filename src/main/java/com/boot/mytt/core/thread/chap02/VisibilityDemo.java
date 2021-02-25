package com.boot.mytt.core.thread.chap02;

import com.boot.mytt.core.util.Tools;
import javafx.scene.effect.Blend;

/**
 * @author renwq
 * @date 2021/2/22 下午11:20
 */
public class VisibilityDemo {

    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        new Thread(new TimeConsumingTask()).start();
        Thread.sleep(10000);
        // 指定的时间内任务没有执行结束的话，就将其取消
        timeConsumingTask.cancel();
    }
}
class TimeConsumingTask implements Runnable{

    private volatile boolean toCancel = false;

    @Override
    public void run() {
        while (!toCancel) {
            if (doExecute()) {
                break;
            }
        }
        if (toCancel) {
            System.out.println("Task was canceled.");
        } else {
            System.out.println("Task Done.");
        }
    }

    private boolean doExecute() {
        System.out.println("Executing...");
        // 模拟实际操作的时间消耗
        Tools.randomPause(50);
        return toCancel;
    }

    public void cancel() {
        toCancel = true;
        System.out.println(this + "canceled.");
    }
}
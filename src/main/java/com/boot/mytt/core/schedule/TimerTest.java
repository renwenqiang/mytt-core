package com.boot.mytt.core.schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author renwq
 * @since 2020/6/27 13:21
 */
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new HelloTimerTask(), 2000, 2000);
    }

    static class HelloTimerTask extends TimerTask{

        @Override
        public void run() {
            System.out.println("hello a");
        }
    }
}

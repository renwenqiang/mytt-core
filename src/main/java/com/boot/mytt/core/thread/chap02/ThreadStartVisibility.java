package com.boot.mytt.core.thread.chap02;

import com.boot.mytt.core.util.Tools;

/**
 * @author renwq
 * @date 2021/2/23 下午11:56
 */
public class ThreadStartVisibility {

    static int data = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                Tools.randomPause(50);
                System.out.println(data);
            }
        };
        data = 1;
        thread.start();
        Tools.randomPause(50);
        data = 2;

    }
}

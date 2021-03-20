package com.boot.mytt.core.thread.chap06;

import java.text.DecimalFormat;

/**
 * @author renwq
 * @date 2021/3/20 23:05
 */
public class TestMain {

    public static void main(String[] args) {
        sendYzm();
    }

    private static void sendYzm() {
        for (int k=0; k<5; k++) {
            new Thread(){
                @Override
                public void run() {
                    int verifyCode = ThreadSpecificSecureRandom.INSTANCE.nextInt(99999);
                    DecimalFormat df = new DecimalFormat("000000");
                    System.out.println(df.format(verifyCode));
                }
            }.start();
        }
    }
}

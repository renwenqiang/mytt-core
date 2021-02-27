package com.boot.mytt.core.thread.chap03;

/**
 * @author renwq
 * @date 2021/2/27 上午11:57
 */
public class ValitileOrderingDemo {
    private int dataA = 0;
    private long dataB = 0L;
    private String dataC = null;
    private volatile boolean ready = false;

    public void writer() {
        dataA = 1;
        dataB = 1L;
        dataC = "Counting...";
        ready = true;
    }

    public int reader() {
        int result = 0;
        boolean allISOK;
        if (ready) {
            allISOK = (1 == dataA) && (1L == dataB) && "Counting...".equals(dataC);
            result = allISOK ? 1 : 2;
        } else {
            result = 3;
        }
        return result;
    }
}

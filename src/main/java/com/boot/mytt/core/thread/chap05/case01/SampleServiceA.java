package com.boot.mytt.core.thread.chap05.case01;

import com.boot.mytt.core.util.Tools;

import java.util.concurrent.CountDownLatch;

/**
 * @author renwq
 * @date 2021/3/3 下午10:25
 */
public class SampleServiceA extends AbstractService {
    public SampleServiceA(CountDownLatch countDownLatch) {
        super(countDownLatch);
    }

    @Override
    protected void doStart() throws Exception {
        Tools.randomPause(1000);
    }
}

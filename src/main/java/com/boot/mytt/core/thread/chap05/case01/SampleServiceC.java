package com.boot.mytt.core.thread.chap05.case01;

import com.boot.mytt.core.util.Tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author renwq
 * @date 2021/3/3 下午10:25
 */
public class SampleServiceC extends AbstractService {
    public SampleServiceC(CountDownLatch countDownLatch) {
        super(countDownLatch);
    }

    @Override
    protected void doStart() throws Exception {
        Tools.randomPause(2000);

        // 省略其他代码

        // 模拟服务器启动失败
        final Random random = new Random();
        int rand = random.nextInt(1000);
        if (rand <= 500) {
            throw new RuntimeException("test");
        }
    }
}

package com.boot.mytt.core.thread.chap02;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author renwq
 * @date 2021/2/21 下午2:00
 */
public class RequestIDGenerator implements CircularSeqGenerator {

    // 保存类的唯一实例
    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();
    private final static short SEQ_UPPER_LIMIT = 999;
    private short sequence = -1;

    // 私有构造器 什么也不做
    private RequestIDGenerator() {

    }

    /**
     * @return 下一个序列号
     */
    @Override
    public short nextSequence() {
        if (sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }

    // 生成一个新的Request ID
    public String nextID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        DecimalFormat df = new DecimalFormat("000");
        // 生成请求序列号
        short sequenceNo = nextSequence();
        return "0049-" + timestamp + "-" + df.format(sequenceNo);
    }

    // 返回该类的唯一实例
    public static RequestIDGenerator getInstance() {
        return INSTANCE;
    }
}

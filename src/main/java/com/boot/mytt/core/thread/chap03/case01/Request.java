package com.boot.mytt.core.thread.chap03.case01;

import java.io.InputStream;

/**
 * @author renwq
 * @date 2021/2/27 下午4:46
 */
public class Request {
    private final long transactionId;
    private final int transactionType;
    private InputStream inputStream;

    public Request(long transactionId, int transactionType) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}

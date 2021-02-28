package com.boot.mytt.core.thread.chap04.case01;

import com.boot.mytt.core.thread.util.Debug;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author renwq
 * @date 2021/2/28 下午5:12
 */
public class DownloadBuffer implements Closeable {

    private long globalOffset;
    private long upperBound;
    private int offset = 0;
    public ByteBuffer byteBuf;
    public Storage storage;

    public DownloadBuffer(long globalOffset, long upperBound, Storage storage) {
        this.globalOffset = globalOffset;
        this.upperBound = upperBound;
        this.storage = storage;
        this.byteBuf = ByteBuffer.allocate(1024 * 1024);
    }

    public void write(ByteBuffer buf) throws IOException {
        int length = buf.position();
        final int capacity = byteBuf.capacity();
        if (offset + length > capacity || length == capacity) {
            flush();
        }
        byteBuf.position(offset);
        buf.flip();
        byteBuf.put(buf);
        offset += length;
    }

    public void flush() throws IOException {
        int length;
        byteBuf.flip();
        length = storage.store(globalOffset, byteBuf);
        byteBuf.clear();
        globalOffset += length;
        offset = 0;
    }

    @Override
    public void close() throws IOException {
        Debug.info("globalOffset:%s,upperBound:%s", globalOffset, upperBound);
        if (globalOffset < upperBound) {
            flush();
        }
    }
}

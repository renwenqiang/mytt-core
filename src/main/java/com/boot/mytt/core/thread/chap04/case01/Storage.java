package com.boot.mytt.core.thread.chap04.case01;

import com.boot.mytt.core.util.Debug;
import com.boot.mytt.core.util.Tools;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author renwq
 * @date 2021/2/28 下午3:17
 */
public class Storage implements Closeable, AutoCloseable {

    private final RandomAccessFile storeFile;
    private final FileChannel storeChannel;
    protected final AtomicLong totalWrites = new AtomicLong(0);

    public Storage(long fileSize, String fileShortName) throws IOException {
        String fullFileName = System.getProperty("java.io.tmpdir") + "/" + fileShortName;
        String localFIleName = createStoreFile(fileSize, fullFileName);
        this.storeFile = new RandomAccessFile(localFIleName, "rw");
        this.storeChannel = storeFile.getChannel();
    }

    public int store(long offset, ByteBuffer byteBuffer) throws IOException {
        int length;
        storeChannel.write(byteBuffer, offset);
        length = byteBuffer.limit();
        totalWrites.addAndGet(length);
        return length;
    }

    public long getTotalWrites() {
        return totalWrites.get();
    }

    private String createStoreFile(final long fileSize, String fullFileName) throws IOException {
        File file = new File(fullFileName);
        Debug.info("create local file: %s", fullFileName);
        RandomAccessFile raf;
        raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(fileSize);
        } finally {
            Tools.silentClose(raf);
        }
        return fullFileName;
    }
    @Override
    public synchronized void close() throws IOException {
        if (storeChannel.isOpen()) {
            Tools.silentClose(storeChannel, storeFile);
        }
    }
}

package com.boot.mytt.core.thread.chap02;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * 本Demo必须使用32位Java虚拟机才能看到非原子操作的效果. <br>
 * 运行本Demo时也可以指定虚拟机参数“-client”
 * @author renwq
 * @date 2021/2/22 下午10:47
 */
public class NonAtomicAssignmentDemo implements Runnable {

    static long value = 0;
    private final long valueToSet;

    public NonAtomicAssignmentDemo(long valueToSet) {
        this.valueToSet = valueToSet;
    }

    public static void main(String[] args) {
        // 线程updateThread1将data更新为0
        Thread thread1 = new Thread(new NonAtomicAssignmentDemo(0L));
        // 线程updateThread2将data更新为-1
        Thread thread2 = new Thread(new NonAtomicAssignmentDemo(-1L));
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        });
        thread1.start();
        thread2.start();
        // 共享变量value的快照（即瞬间值）
        long snapshot;
        while (0 ==(snapshot = value) || -1 == snapshot) {
            // 不进行实际的输出，仅仅是为了阻止JIT编译器做循环不变表达式外提优化
            printStream.println(snapshot);
        }
        System.err.printf("Unexpected data: %d(0x%016x)", snapshot, snapshot);
        printStream.close();
        System.exit(0);
    }

    @Override
    public void run() {
        for (;;) {
            value = valueToSet;
        }
    }
}

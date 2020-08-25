package com.boot.mytt.core.bean;

/**
 * @author renwq
 * @since 2020/6/15 22:05
 */
public class NettyOne {
    public static void main(String[] args) {
        byte seqBits = 1;
        long a = -1L ^ -1L << seqBits;
        long b = -1L ^ -1L << seqBits;
        long c = -1L ^ -1L << seqBits;
        System.out.println(seqBits);
        System.out.println(Long.toBinaryString(5L));
        System.out.println(b);
        System.out.println(c);
    }
}

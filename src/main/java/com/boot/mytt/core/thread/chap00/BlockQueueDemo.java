package com.boot.mytt.core.thread.chap00;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author renwq
 * @date 2021/3/23 15:48
 */
public class BlockQueueDemo {

    private static void atomicReferenceUsage1() {
        AtomicReference<SimpleObject> atomicReference = new AtomicReference<>();
        atomicReference.set(new SimpleObject("张三", 22));
        SimpleObject simpleObject = atomicReference.get();
        System.out.println(simpleObject);
    }

    private static void atomicReferenceUsage2() {
        AtomicReference<SimpleObject> atomicReference = new AtomicReference<>(new SimpleObject("张三", 23));
        SimpleObject simpleObject = atomicReference.get();
        System.out.println(simpleObject);
    }

    private static void atomicReferenceUsage3() {
        SimpleObject test = new SimpleObject("test3" , 30);
        AtomicReference<SimpleObject> atomicReference2 = new AtomicReference<>(test);
        Boolean bool = atomicReference2.compareAndSet(test, new SimpleObject("test4", 40));
        System.out.println("simpleObject  Value: " + bool);
        System.out.println("atomicReference2: " + atomicReference2.get());
    }

    public static void main(String[] args) {
        atomicReferenceUsage3();
    }
}

package com.boot.mytt.core.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author renwq
 * @date 2021/2/19 18:32
 */
public class Test01 {

    public static void main(String[] args) throws InterruptedException {
//        Map<String, String> map = new HashMap<>();
//        map.forEach((k,v) -> System.out.println(k));
//        for (String k: map.keySet()) {
//            System.out.println(k);
//        }
//        List<String> strList = new ArrayList<>();
//        for (String str: strList) {
//            System.out.println(str);
//        }
//        System.out.println(1);
//        strList.forEach(k -> System.out.println(k));
//        System.out.println(2);
//        strList.stream().filter(k -> !k.isEmpty()).collect(Collectors.toList());
//        System.out.println(3);
//        List<String> strList2 = strList.stream().filter(k -> !k.isEmpty()).collect(Collectors.toList());
//        System.out.println(4);
//        strList.stream().filter(k -> k.isEmpty()).collect(Collectors.toList());
//        System.out.println(5);

        Helper helper = new Helper(4);
        helper.start();
        Thread.sleep(2000);
    }

    static class Helper extends Thread{
        public Helper(int k) {
            System.out.println("线程执行...");
            setDaemon(true);
            setName("线程-: " + k);
        }

        @Override
        public void run() {
            System.out.printf("thread running...,name is: " + Thread.currentThread().getName());
        }
    }
}

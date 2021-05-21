package com.boot.mytt.core.test;

import jdk.nashorn.internal.runtime.ECMAException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class TestMain {

//    public static AtomicReference<Map<String, Integer>> atomicHang = new AtomicReference<>(new HashMap<>());

    private static int k = 1;

    public static void main(String[] args) {
//        System.out.println("print parameter on next line: ");
//        System.out.println(args);

//        Calendar calendar = Calendar.getInstance();
//        calendar.set( Calendar.HOUR_OF_DAY, 11 ); // 控制时
//        calendar.set( Calendar.MINUTE, 0 );    // 控制分
//        calendar.set( Calendar.SECOND, 0 );    // 控制秒
        Date time = new Date();    // 得出执行任务的时间,此处为今天的12：00：00
        Random random = new Random();
         new Timer().scheduleAtFixedRate(new TimerTask() {
             @Override
             public void run() {
                 k++;
                 System.out.println(k);
                 if (k == 5) {
                     System.out.println("if: " + k);
                     return;
                 } else {
                     System.out.println("else: " + k);
                 }
                 System.out.println("last: " + k);
                 if (random.nextInt(100) > 80) {
                     try {
                         int a = 0;
                         int b = 10 / a;
                     } catch (ECMAException e) {
                         e.printStackTrace();
                         System.out.println("抛出异常ECMAException");
                     } catch (Exception e) {
                         e.printStackTrace();
                         System.out.println("抛出异常Exception");
                     }
                 }
             }
         }, time, 1000 * 1);


//        Long abc = Long.valueOf("707918395168796672");
//        double a = 0.3;
//        System.out.println(1 - a);

//        Double a = 0D;
//        System.out.println(0D <= 0.000D);

//        Map<String, Integer> map = atomicHang.get();
//        System.out.println();

//        List<String> strList = new ArrayList<>();
//        for (int k=1; k<=5; k++) {
//            strList.add("" + k);
//        }
//        System.out.println(strList);
//        int batchNum = 1;
//        Map<String, List<String>> tmpMap = new HashMap<>();
//
//        List<String> listT = new ArrayList<>();
//        for (int k=1; k<=strList.size(); k++) {
//            listT.add(strList.get(k-1));
//            if (k%batchNum == 0) {
//                tmpMap.put("" + k, listT);
//                listT = new ArrayList<>();
//            }else if (k==strList.size()) {
//                tmpMap.put("" + k, listT);
//            }
//        }
//        System.out.println(tmpMap);
    }
}

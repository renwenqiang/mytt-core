package com.boot.mytt.core.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class TestMain {

    public static AtomicReference<Map<String, Integer>> atomicHang = new AtomicReference<>(new HashMap<>());

    public static void main(String[] args) {
//        Long abc = Long.valueOf("707918395168796672");
//        double a = 0.3;
//        System.out.println(1 - a);

//        Double a = 0D;
//        System.out.println(0D <= 0.000D);

//        Map<String, Integer> map = atomicHang.get();
//        System.out.println();

        List<String> strList = new ArrayList<>();
        for (int k=1; k<=26; k++) {
            strList.add("" + k);
        }
        System.out.println(strList);
        int batchNum = 10;
        Map<String, List<String>> tmpMap = new HashMap<>();

        List<String> listT = new ArrayList<>();
        for (int k=1; k<=strList.size(); k++) {
            listT.add(strList.get(k-1));
            if (k%batchNum == 0) {
                tmpMap.put("" + k, listT);
                listT = new ArrayList<>();
            }else if (k==strList.size()) {
                tmpMap.put("" + k, listT);
            }
        }
        System.out.println(tmpMap);
    }
}

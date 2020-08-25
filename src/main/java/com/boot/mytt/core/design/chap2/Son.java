package com.boot.mytt.core.design.chap2;

import java.util.Collection;
import java.util.Map;

/**
 * @author renwq
 * @since 2020/7/3 22:25
 */
public class Son extends Father {
    public Collection doSomething(Map map) {
        System.out.println("子类被执行");
        return map.values();
    }
}

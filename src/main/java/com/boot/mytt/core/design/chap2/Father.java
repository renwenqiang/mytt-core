package com.boot.mytt.core.design.chap2;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author renwq
 * @since 2020/7/3 22:25
 */
public class Father {
    public Collection doSomething(HashMap map) {
        System.out.println("父类被执行");
        return map.values();
    }
}

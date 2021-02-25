package com.boot.mytt.core.staticvar;

/**
 * @author renwq
 * @date 2021/2/18 18:21
 */
public class TestMain {

    public static void main(String[] args) {
        Test1 t1 = new Test1();
        t1.fn1();
        System.out.println(CommonData.dcmap);
        Test2 t2 = new Test2();
        t2.fn2();
        System.out.println(CommonData.dcmap);

    }
}

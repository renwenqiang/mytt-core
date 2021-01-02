package com.boot.mytt.core.cache.maps;

/**
 * @author renwq
 * @date 2021/1/2 1:15
 */
public class Test03 {

    public void test01() {
        Test02 t2 = new Test02(){
            @Override
            protected boolean fn() {
                return true;
            }
        };
    }
}

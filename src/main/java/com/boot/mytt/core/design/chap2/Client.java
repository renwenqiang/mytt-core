package com.boot.mytt.core.design.chap2;

import java.util.HashMap;

/**
 * @author renwq
 * @since 2020/7/3 22:16
 */
public class Client {
    public static void main(String[] args) {
        Son father = new Son();
        HashMap hashMap = new HashMap();
        father.doSomething(hashMap);
//        Soldier soldier = new Soldier();
//        soldier.setGun(new RifleGun());
//        soldier.killEnemy();
    }
}

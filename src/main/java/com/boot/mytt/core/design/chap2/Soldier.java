package com.boot.mytt.core.design.chap2;

/**
 * @author renwq
 * @since 2020/7/3 22:10
 */
public class Soldier {
    private AbstractGun gun;
    public void setGun(AbstractGun gun) {
        this.gun = gun;
    }
    public void killEnemy() {
        System.out.println("士兵开始杀敌了啊！");
        gun.shoot();
    }
}

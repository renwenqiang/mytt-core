package com.boot.mytt.core.design.chap11;

/**
 * @author renwq
 * @since 2020/7/5 16:31
 */
public class Client {
    public static void main(String[] args) {
        IGamePlayer proxy = new GamePlayerProxy("张三");
        proxy.login("admin", "111");
        proxy.killBoss();
        proxy.upgrade();
    }
}

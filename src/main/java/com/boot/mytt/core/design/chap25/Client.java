package com.boot.mytt.core.design.chap25;

/**
 * @author renwq
 * @since 2020/7/8 0:14
 */
public class Client {

    public static void main(String[] args) {
        // TODO 这里逻辑不是很明白： 重载/重写/覆盖/覆写 显示/隐式 作用域等的问题
        // TODO 访问者模式：后期需要再细看一下 参考《设计模式之禅》第328页
        AbstractActor actor = new OldActor();
        Role role = new KongfuRole();
        actor.act(role);
        actor.act(new KongfuRole());

        long a = 88;
    }
}

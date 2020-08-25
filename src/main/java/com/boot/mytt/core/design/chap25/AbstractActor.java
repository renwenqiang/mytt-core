package com.boot.mytt.core.design.chap25;

/**
 * @author renwq
 * @since 2020/7/7 23:59
 */
public abstract class AbstractActor {

    public void act(Role role) {
        System.out.println("演员可以演任何角色");
    }

    public void act(KongfuRole role) {
        System.out.println("演员可以演功夫角色");
    }
}

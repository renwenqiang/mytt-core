package com.boot.mytt.core.design.chap8;

/**
 * 抽象工厂类
 *
 * @author renwq
 * @since 2020/7/4 22:13
 */
public abstract class Creator {
    public abstract  <T extends Product> T createProduct(Class<T> clazz);
}

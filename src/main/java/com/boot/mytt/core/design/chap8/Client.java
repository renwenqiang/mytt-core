package com.boot.mytt.core.design.chap8;

/**
 * 客户端调用示例
 *
 * @author renwq
 * @since 2020/7/4 22:12
 */
public class Client {
    public static void main(String[] args) {
        Creator concreteCreator = new ConcreteCreator();
        Product product = concreteCreator.createProduct(ConcreteProduct1.class);
        // 继续处理业务

        // 扩展 1 ：简单工厂模式 / 静态工厂模式
        // 扩展 2 ：升级多个工厂类
        // 扩展 3 ：替代单例模式
        // 扩展 4 ：延迟初始化
    }
}

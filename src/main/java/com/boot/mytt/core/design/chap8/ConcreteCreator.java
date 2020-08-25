package com.boot.mytt.core.design.chap8;

/**
 * 具体工厂类
 *
 * @author renwq
 * @since 2020/7/4 21:47
 */
public class ConcreteCreator extends Creator {

    // 创建一个产品对象，其输入参数类型通常为String, Enum, Class等，当然也可为空
    @Override
    public <T extends Product> T createProduct(Class<T> clazz) {
        Product product = null;
        try {
            product = (Product)Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            // 异常处理
        }
        return (T)product;
    }
}
